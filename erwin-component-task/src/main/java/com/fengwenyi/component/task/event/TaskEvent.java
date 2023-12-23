package com.fengwenyi.component.task.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-01
 */
public class TaskEvent extends ApplicationEvent {
    private static final long serialVersionUID = -4410405037516480189L;

    public TaskEvent(Object source) {
        super(source);
    }
}
