package com.fengwenyi.erwin.component.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fengwenyi.erwin.component.config.entity.ConfigEntity;
import com.fengwenyi.erwin.component.config.mp.IMpConfigService;
import com.fengwenyi.erwin.component.config.service.IConfigService;
import com.fengwenyi.erwin.component.config.util.ConfigUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-19
 */
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements IConfigService {

    private final IMpConfigService mpConfigService;

    @Override
    public String queryJson(String organCode, String configCode) {
        ConfigEntity configEntity = queryEnable(organCode, configCode);
        if (Objects.isNull(configEntity)) {
            return "";
        }
        return configEntity.getConfigJson();
    }

    @Override
    public ConfigEntity queryEnable(String organCode, String configCode) {
        return mpConfigService.getOne(
                new LambdaQueryWrapper<ConfigEntity>()
                        .eq(ConfigEntity::getOrganCode, organCode)
                        .eq(ConfigEntity::getConfigCode, configCode)
                        .eq(ConfigEntity::getStatus, true)
        );
    }

    @Override
    public <T> Map<String, T> queryMap(String organCode, String configCode, Class<T> valueType) {
        String json = queryJson(organCode, configCode);
        if (StringUtils.hasText(json)) {
            return ConfigUtils.getMap(json, valueType);
        }
        return null;
    }

    @Override
    public <T> List<T> queryList(String organCode, String configCode, Class<T> valueType) {
        String json = queryJson(organCode, configCode);
        if (StringUtils.hasText(json)) {
            return ConfigUtils.getList(json, valueType);
        }
        return null;
    }

    @Override
    public <T> T queryObject(String organCode, String configCode, Class<T> valueType) {
        String json = queryJson(organCode, configCode);
        if (StringUtils.hasText(json)) {
            return ConfigUtils.getObject(json, valueType);
        }
        return null;
    }

}
