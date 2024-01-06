package com.fengwenyi.erwin.component.log.model.dto;

import com.fengwenyi.erwin.component.log.model.enums.LogTypeEnum;
import com.fengwenyi.erwin.component.log.model.enums.LogLevelEnum;
import com.fengwenyi.erwin.component.log.model.enums.LogWhoTypeEnum;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-14
 */
@Data
public class LogDto {


    // 机构编码
    private String organCode;

    // 业务ID
    private String bizId;

    // 业务类型
    private String bizType;

    // 应用编码
    private String applicationCode;

    // 日志关键字
    private String logKey;

    // 日志业务ID
    private String logBizId;

    // 方法
    private String method;

    // 操作
    private String action;

    // 日志级别
    private LogLevelEnum logLevel;

    // 操作人类型
    private LogWhoTypeEnum whoType;

    // 用户ID
    private String userId;

    // 账户ID
    private String accountId;

    // 账号
    private String accountNo;

    // 卡号
    private String cardNo;

    // 登记号
    private String pmiNo;

    // 用户名字
    private String userName;

    // 日志类型
    private LogTypeEnum logType;

    // 请求IP
    private String requestIp;

    // 渠道编码
    private String channelCode;

    // 地址
    private String url;

    // 参数
    private Object param;

    // 数据
    private Object data;

    // 错误信息
    private String message;

    // 花费时间
    private Duration spentTime;

    private LocalDateTime logDateTime = LocalDateTime.now();

}
