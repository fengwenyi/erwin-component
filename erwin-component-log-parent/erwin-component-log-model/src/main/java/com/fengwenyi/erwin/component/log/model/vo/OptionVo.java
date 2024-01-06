package com.fengwenyi.erwin.component.log.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-20
 */
@ApiModel(description = "选项-vo")
@Data
public class OptionVo {

    @ApiModelProperty("编码，选项值")
    private String code;

    @ApiModelProperty("描述，用于展示")
    private String msg;

}
