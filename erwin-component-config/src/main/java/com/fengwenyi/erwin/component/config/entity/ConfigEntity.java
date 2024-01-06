package com.fengwenyi.erwin.component.config.entity;

import info.hxgy.component.common.jpa.base.BaseBizEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-09-08
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "hc_config")
public class ConfigEntity extends BaseBizEntity {

    private static final long serialVersionUID = -5383748714643376820L;

    private String organCode;

    private String configCode;

    private String configName;

    private String configJson;

    private String moreContent;

}
