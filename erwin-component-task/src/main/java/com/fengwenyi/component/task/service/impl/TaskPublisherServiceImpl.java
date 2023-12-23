package com.fengwenyi.component.task.service.impl;

import com.fengwenyi.component.task.dto.TaskDto;
import com.fengwenyi.component.task.event.TaskEvent;
import com.fengwenyi.component.task.service.ITaskPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-01
 */
@Service
@RequiredArgsConstructor
public class TaskPublisherServiceImpl implements ITaskPublisherService {

    private final ApplicationContext applicationContext;

    @Override
    public void taskEvent(TaskDto dto) {
        applicationContext.publishEvent(new TaskEvent(dto));
    }
}
