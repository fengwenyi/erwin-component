package com.fengwenyi.erwin.component.log.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志记录
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecord {

    /**
     * 业务类型
     */
    String bizType() default "";

    /**
     * 日志key
     */
    String logKey() default "";

    /**
     * 服务
     */
    String app() default "";

    /**
     * 机构编码，可以取变量，例如：#dto.organCode
     */
    String organCode() default ""; // 可以取变量

    /**
     * 用户姓名，可以取变量，例如：#dto.name
     */
    String userName() default ""; // 可以取变量

    /**
     * 卡号，可以取变量，例如：#dto.cardNo
     */
    String cardNo() default ""; // 可以取变量

    /**
     * 登记号，可以取变量，例如：#dto.pmiNo
     */
    String pmiNo() default ""; // 可以取变量

    /**
     * 业务ID，可以取变量，例如：#dto.id
     */
    String bizId() default ""; // 可以取变量

    /**
     * 业务日志ID，可以取变量，例如：#dto.logBizId
     */
    String logBizId() default ""; // 可以取变量

}
