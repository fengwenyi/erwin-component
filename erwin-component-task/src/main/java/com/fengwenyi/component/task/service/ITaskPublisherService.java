package com.fengwenyi.component.task.service;

import com.fengwenyi.component.task.dto.TaskDto;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-01
 */
public interface ITaskPublisherService {

    void taskEvent(TaskDto dto);

}
