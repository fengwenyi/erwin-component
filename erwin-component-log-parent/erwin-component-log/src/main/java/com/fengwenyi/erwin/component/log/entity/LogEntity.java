package com.fengwenyi.erwin.component.log.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import info.hxgy.component.common.jpa.base.BaseEntity;
import com.fengwenyi.erwin.component.log.model.enums.LogLevelEnum;
import com.fengwenyi.erwin.component.log.model.enums.LogTypeEnum;
import com.fengwenyi.erwin.component.log.model.enums.LogWhoTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-07
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "hc_log")
public class LogEntity extends BaseEntity {

    private static final long serialVersionUID = -1661383045025081224L;

    // 机构编码
    @Column(name = "organ_code")
    private String organCode;

    // 业务ID
    @Column(name = "biz_id")
    private String bizId;

    // 业务类型
    @Column(name = "biz_type")
    private String bizType;

    // 应用编码
    @Column(name = "application_code")
    private String applicationCode;

    // 日志关键字
    @Column(name = "log_key")
    private String logKey;

    // 方法
    @Column(name = "method")
    private String method;

    // 操作
    @Column(name = "action")
    private String action;

    // 日志业务ID
    @Column(name = "log_biz_id")
    private String logBizId;

    // 操作人类型
    @Column(name = "who_type")
    @Enumerated(EnumType.STRING)
    private LogWhoTypeEnum whoType;

    // 日志级别
    @Column(name = "log_level")
    @Enumerated(EnumType.STRING)
    private LogLevelEnum logLevel;

    // 用户ID
    @Column(name = "user_id")
    private String userId;

    // 账户ID
    @Column(name = "account_id")
    private String accountId;

    // 账号
    @Column(name = "account_no")
    private String accountNo;

    // 请求IP
    @Column(name = "request_ip")
    private String requestIp;

    // 卡号
    @Column(name = "card_no")
    private String cardNo;

    // 登记号
    @Column(name = "pmi_no")
    private String pmiNo;

    // 用户名
    @Column(name = "user_name")
    private String userName;

    // 日志类型
    @Column(name = "log_type")
    @Enumerated(EnumType.STRING)
    private LogTypeEnum logType;

    // 渠道编码
    @Column(name = "channel_code")
    private String channelCode;

    // 地址
    @Column(name = "url")
    private String url;

    // 参数
    @Column(name = "param")
    private String param;

    // 数据
    @Column(name = "data")
    private String data;

    // 错误信息
    @Column(name = "message")
    private String message;

    // 花费秒数
    @Column(name = "spent_seconds")
    private Double spentSeconds;

    // 日志时间
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "log_date_time")
    private LocalDateTime logDateTime;

}
