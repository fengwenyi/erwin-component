package com.fengwenyi.component.task.lister;

import com.fengwenyi.component.task.dto.TaskDto;
import com.fengwenyi.component.task.service.ITaskService;
import com.fengwenyi.javalib.convert.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-01
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TaskLister {

    private final ITaskService taskService;

    @EventListener
    @Async
    public void taskEvent(TaskEvent event) {

        Object source = event.getSource();

        if (Objects.nonNull(source)) {
            try {
                taskService.save((TaskDto) source);
                log.info("taskEvent, saved, source: [{}]", JsonUtils.convertString(source));
            } catch (Exception e) {
                log.error("taskEvent, save failed, source: [{}], exception: {}",
                        JsonUtils.convertString(source), e.getLocalizedMessage(), e);
            }
        }
    }

}
