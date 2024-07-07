package com.fengwenyi.erwin.component.config.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fengwenyi.erwin.component.common.mybatis_plus.base.BaseBizEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-19
 */
@Getter
@Setter
@ToString
@TableName("ec_config")
public class ConfigEntity extends BaseBizEntity {

    @Serial
    private static final long serialVersionUID = 936792872471992220L;

    private String organCode;

    private String configCode;

    private String configName;

    private String configJson;
}
