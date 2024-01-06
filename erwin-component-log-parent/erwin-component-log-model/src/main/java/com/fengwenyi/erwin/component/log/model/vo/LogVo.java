package com.fengwenyi.erwin.component.log.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-19
 */
@ApiModel(description = "日志-vo")
@Data
public class LogVo {

    @ApiModelProperty("日志ID")
    private String id;

    @ApiModelProperty("日志时间")
    private String logDateTime;

    @ApiModelProperty("业务类型")
    private String bizType;

    @ApiModelProperty("业务类型描述")
    private String bizTypeMsg;

    @ApiModelProperty("机构编码")
    private String organCode;

    @ApiModelProperty("机构名称")
    private String organName;

    @ApiModelProperty("业务ID")
    private String bizId;

    @ApiModelProperty("日志业务ID")
    private String logBizId;

    @ApiModelProperty("接口key")
    private String logKey;

    @ApiModelProperty("接口")
    private String logKeyMsg;

    @ApiModelProperty("方法")
    private String method;

    @ApiModelProperty("操作")
    private String actionMsg;

    @ApiModelProperty("服务编码")
    private String applicationCode;

    @ApiModelProperty("服务")
    private String applicationMsg;

    // 操作人类型
    @ApiModelProperty("操作人类型")
    private String whoType;

    // 日志级别
    @ApiModelProperty("日志级别")
    private String logLevel;

    // 用户ID
    @ApiModelProperty("用户ID")
    private String userId;

    // 账户ID
    @ApiModelProperty("账户ID")
    private String accountId;

    // 账号
    @ApiModelProperty("账号")
    private String accountNo;

    // 卡号
    @ApiModelProperty("卡号")
    private String cardNo;

    // 登记号
    @ApiModelProperty("登记号")
    private String pmiNo;

    // 用户姓名
    @ApiModelProperty("用户姓名")
    private String userName;

    // 日志类型
    @ApiModelProperty("日志类型")
    private String logType;

    // 日志类型描述
    @ApiModelProperty("日志类型描述")
    private String logTypeMsg;

    // 请求IP
    @ApiModelProperty("请求IP")
    private String requestIp;

    // 位置
    @ApiModelProperty("位置")
    private String position;

    // 渠道编码
    @ApiModelProperty("渠道名称")
    private String channelName;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("请求参数")
    private String param;

    @ApiModelProperty("返回数据")
    private String data;

    @ApiModelProperty("错误信息")
    private String message;

    @ApiModelProperty("花费时间（秒）")
    private Double spentSeconds;

}
