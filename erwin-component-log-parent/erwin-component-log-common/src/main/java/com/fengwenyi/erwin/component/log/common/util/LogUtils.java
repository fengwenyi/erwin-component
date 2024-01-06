package com.fengwenyi.erwin.component.log.common.util;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-22
 */
public class LogUtils {

    public static String getMethod() {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return className + "." + methodName + "()";
    }

}
