package com.fengwenyi.erwin.component.log.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
public class LogEvent extends ApplicationEvent {
    private static final long serialVersionUID = -2215086245675712390L;

    public LogEvent(Object source) {
        super(source);
    }
}
