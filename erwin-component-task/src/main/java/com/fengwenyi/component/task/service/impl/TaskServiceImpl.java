package com.fengwenyi.component.task.service.impl;

import com.fengwenyi.component.task.bo.TaskConfigBo;
import com.fengwenyi.component.task.dto.TaskDto;
import com.fengwenyi.component.task.dto.TaskQueryDto;
import com.fengwenyi.component.task.entity.TaskEntity;
import com.fengwenyi.component.task.entity.TaskLogEntity;
import com.fengwenyi.component.task.enums.TaskHandleResultEnum;
import com.fengwenyi.component.task.enums.TaskStatusEnum;
import com.fengwenyi.component.task.jk.ITaskHandler;
import com.fengwenyi.component.task.repository.ITaskLogRepository;
import com.fengwenyi.component.task.repository.ITaskRepository;
import com.fengwenyi.component.task.service.ITaskService;
import com.fengwenyi.javalib.convert.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-30
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements ITaskService {

    private final ITaskRepository taskRepository;
    private final IConfigService configService;
    private final ITaskLogRepository taskLogRepository;

    @Override
    public void save(TaskDto dto) {

        if (Objects.isNull(dto)) {
            return;
        }

        TaskConfigBo configBo = getTaskConfigBo(dto.getBizType());

        if (Objects.isNull(configBo)) {
            log.error("task add failed, taskConfigBo not config, bizType: [{}]", dto.getBizType());
            return;
        }

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(IdWorker.getId());
        taskEntity.setStatus(true);
        taskEntity.setBizId(dto.getBizId());
        taskEntity.setBizType(dto.getBizType());
        taskEntity.setParam(dto.getParam());
        taskEntity.setTaskStatus(TaskStatusEnum.created);
        taskEntity.setRetryCount(0);
        taskEntity.setPlanExecuteDateTime(calculatePlanExecuteTime(1, configBo.getIntervalMinutes()));

        taskRepository.save(taskEntity);
    }

    public List<TaskEntity> queryList(TaskQueryDto dto) {

        boolean sortDesc = dto.getSortDesc();
        int size = dto.getSize();
        LocalDateTime startDateTime = dto.getStartDateTime(); // 可以为空
        String bizType = dto.getBizType();

        Sort.Direction direction;

        if (sortDesc) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }

        Sort sort = Sort.by(direction, "createDateTime");

        PageRequest pageRequest = PageRequest.of(0, size, sort);

        Specification<TaskEntity> specification = (r, query, builder) -> {

            List<Predicate> list = new ArrayList<>();

            list.add(builder.equal(r.get("bizType"), bizType));

            CriteriaBuilder.In<TaskStatusEnum> in = builder.in(r.get("taskStatus"));
            in.value(TaskStatusEnum.created).value(TaskStatusEnum.retrying);
            list.add(in);

            list.add(builder.lessThanOrEqualTo(r.get("planExecuteDateTime"), LocalDateTime.now()));

            if (Objects.nonNull(startDateTime)) {
                list.add(builder.greaterThanOrEqualTo(r.get("createDateTime"), startDateTime));
            }

            query.where(list.toArray(new Predicate[0]));

            return null;
        };

        Page<TaskEntity> pageResult = taskRepository.findAll(specification, pageRequest);

        return pageResult.getContent();
    }

    @Override
    public void execute(TaskQueryDto dto) {

        if (Objects.isNull(dto)) {
            log.error("task execute failed, TaskQueryDto is null");
            return;
        }

        String bizType = dto.getBizType();

        if (!StringUtils.hasText(bizType)) {
            log.error("task execute failed, bizType is null");
            return;
        }

        TaskConfigBo configBo = getTaskConfigBo(bizType);

        if (Objects.isNull(configBo)) {
            log.error("task execute failed, taskConfigBo not config, bizType: [{}]", bizType);
            return;
        }

        ITaskHandler handler = (ITaskHandler) SpringContextSupport.getBean(configBo.getBeanHandler());

        List<TaskEntity> taskEntityList = queryList(dto);

        for (TaskEntity taskEntity : taskEntityList) {
            //  update taskStatus running
            updateTaskIng(taskEntity);

            Object data = handler.execute(taskEntity.getParam());
            TaskHandleResultEnum resultEnum = handler.result(data);

            handleTaskResult(taskEntity, resultEnum, configBo, data);
        }

    }

    private TaskConfigBo getTaskConfigBo(String bizType) {

        List<TaskConfigBo> configBoList = configService.queryList(ComponentConstant.KEY_ORGAN_COMMON, ComponentConstant.KEY_CONFIG_TASK, TaskConfigBo.class);

        for (TaskConfigBo configBo : configBoList) {
            if (bizType.equals(configBo.getBizType())) {
                return configBo;
            }
        }
        return null;
    }

    private void handleTaskResult(TaskEntity taskEntity, TaskHandleResultEnum resultEnum, TaskConfigBo configBo, Object data) {

        if (Objects.equals(resultEnum, TaskHandleResultEnum.succeeded)) {
            taskEntity.setTaskStatus(TaskStatusEnum.succeeded);
        } else {
            if (taskEntity.getRetryCount() > configBo.getMaxRetryCount()) {
                if (Objects.equals(TaskHandleResultEnum.succeeded, resultEnum)) {
                    taskEntity.setTaskStatus(TaskStatusEnum.succeeded);
                } else {
                    taskEntity.setTaskStatus(TaskStatusEnum.failed);
                }
            } else {
                taskEntity.setTaskStatus(TaskStatusEnum.retrying);
                taskEntity.setPlanExecuteDateTime(calculatePlanExecuteTime(taskEntity.getRetryCount() + 1, configBo.getIntervalMinutes()));
                taskEntity.setRetryCount(taskEntity.getRetryCount() + 1);
            }
        }

        taskRepository.save(taskEntity);

        saveTaskLog(taskEntity, resultEnum, configBo.getNotSaveData(), data);
    }

    private void saveTaskLog(TaskEntity taskEntity, TaskHandleResultEnum resultEnum, Boolean notSaveData, Object data) {
        TaskLogEntity taskLogEntity = new TaskLogEntity();
        taskLogEntity.setId(IdWorker.getId());
        taskLogEntity.setBizType(taskEntity.getBizType());
        taskLogEntity.setBizId(taskEntity.getBizId());
        taskLogEntity.setParam(taskEntity.getParam());
        taskLogEntity.setResult(resultEnum);
        if (BoolUtils.isFalse(notSaveData)) {
            taskLogEntity.setData(JsonUtils.convertString(data));
        }
        taskLogRepository.save(taskLogEntity);
    }

    /**
     * 计算计划执行时间
     */
    private LocalDateTime calculatePlanExecuteTime(int currentTryCount, List<Integer> intervalMinutes) {
        long defaultInterval = 5L;
        LocalDateTime now = LocalDateTime.now();
        if (CollectionUtils.isEmpty(intervalMinutes)) {
            return now.plusMinutes(defaultInterval);
        }
        if (currentTryCount - 1 > intervalMinutes.size()) {
            return now.plusMinutes(defaultInterval);
        }
        Integer interval = intervalMinutes.get(currentTryCount - 1);
        return now.plusMinutes(interval);
    }

    private void updateTaskIng(TaskEntity taskEntity) {
        taskEntity.setTaskStatus(TaskStatusEnum.running);
        taskEntity.setRetryCount(taskEntity.getRetryCount() + 1);
        taskRepository.save(taskEntity);
    }

}
