package com.fengwenyi.component.task.jk;

import com.fengwenyi.component.task.enums.TaskHandleResultEnum;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-01
 */
public interface ITaskHandler {

    Object execute(String param);

    TaskHandleResultEnum result(Object data);

}
