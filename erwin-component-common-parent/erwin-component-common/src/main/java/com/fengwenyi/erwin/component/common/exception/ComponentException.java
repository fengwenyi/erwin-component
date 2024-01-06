package com.fengwenyi.erwin.component.common.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-13
 */
@Getter
public class ComponentException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 532992306250747519L;

    private Throwable t;
    private String code;

    public ComponentException(Throwable t) {
        super(t.getMessage());
        this.t = t;
    }

    public ComponentException(String message, Throwable t) {
        super(message);
        this.t = t;
    }

    public ComponentException(String message) {
        super(message);
    }

    public ComponentException(String code, String message, Throwable t) {
        super(message);
        this.t = t;
        this.code = code;
    }

    public ComponentException(String code, String message) {
        super(message);
        this.code = code;
    }
}
