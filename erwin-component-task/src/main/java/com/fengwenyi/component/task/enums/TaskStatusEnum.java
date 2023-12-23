package com.fengwenyi.component.task.enums;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-30
 */
public enum TaskStatusEnum {

    created, running, succeeded, failed, stopping, stopped, retrying

    // ing: running, stopping, retrying
    // 最终：succeeded, failed, stopped
    // start: created

}
