package com.fengwenyi.erwin.component.config.repository;

import com.fengwenyi.erwin.component.config.entity.ConfigEntity;
import info.hxgy.component.common.jpa.base.IBaseRepository;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-29
 */
public interface IConfigRepository extends IBaseRepository<ConfigEntity> {

    ConfigEntity findByOrganCodeAndConfigCodeAndStatus(String organCode, String configCode, boolean status);

}