package com.fengwenyi.component.task.service;

import com.fengwenyi.component.task.dto.TaskDto;
import com.fengwenyi.component.task.dto.TaskQueryDto;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-30
 */
public interface ITaskService {

    void save(TaskDto dto);

    void execute(TaskQueryDto dto);

}
