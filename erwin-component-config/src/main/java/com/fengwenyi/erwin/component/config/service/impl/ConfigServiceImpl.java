package com.fengwenyi.erwin.component.config.service.impl;

import com.fengwenyi.erwin.component.config.entity.ConfigEntity;
import com.fengwenyi.erwin.component.config.repository.IConfigRepository;
import com.fengwenyi.erwin.component.config.service.IConfigService;
import com.fengwenyi.erwin.component.config.util.ConfigUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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

    private final IConfigRepository configRepository;

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
        Assert.notNull(organCode, "organCode must be not null");
        Assert.notNull(configCode, "configCode must be not null");
        return configRepository.findByOrganCodeAndConfigCodeAndStatus(organCode, configCode, true);
    }

    @Override
    public <T> T queryObject(String organCode, String configCode, Class<T> clazz) {
        String json = queryJson(organCode, configCode);
        if (!StringUtils.hasText(json)) {
            return null;
        }
        return ConfigUtils.getObject(json, clazz);
    }

    @Override
    public <T> List<T> queryList(String organCode, String configCode, Class<T> clazz) {
        String json = queryJson(organCode, configCode);
        if (!StringUtils.hasText(json)) {
            return null;
        }
        return ConfigUtils.getList(json, clazz);
    }

    @Override
    public <T> Map<String, T> queryMap(String organCode, String configCode) {
        String json = queryJson(organCode, configCode);
        if (!StringUtils.hasText(json)) {
            return null;
        }
        return ConfigUtils.getMap(json);
    }

}
