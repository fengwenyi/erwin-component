package com.fengwenyi.erwin.component.log.model.dto;

import info.hxgy.component.common.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-19
 */
@ApiModel(description = "日志查询-dto")
@Getter
@Setter
@ToString
public class LogQueryDto extends BaseDto {

    @ApiModelProperty("日志开始时间，格式：yyyy-MM-dd HH:mm:ss")
    private String logStartTime;

    @ApiModelProperty("日志结束时间，格式：yyyy-MM-dd HH:mm:ss")
    private String logEndTime;

    @ApiModelProperty("业务类型，可以对业务进行权限划分，如：挂号，医嘱退费，门诊缴费")
    private String bizType;

    @ApiModelProperty("机构编码")
    private String organCode;

    @ApiModelProperty("业务ID")
    private String bizId;

    @ApiModelProperty("日志业务ID")
    private String logBizId;

    @ApiModelProperty("日志key，具体的接口，如挂号锁号接口，挂号取消接口")
    private String logKey;

    @ApiModelProperty("服务编码")
    private String applicationCode;

    // 日志级别
    @ApiModelProperty("日志级别")
    private String logLevel;

    // 卡号
    @ApiModelProperty("卡号")
    private String cardNo;

    // 登记号
    @ApiModelProperty("登记号")
    private String pmiNo;

}
