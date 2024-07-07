package com.fengwenyi.erwin.component.sample.config.service;

import com.fengwenyi.erwin.component.config.entity.ConfigEntity;
import com.fengwenyi.erwin.component.config.service.IConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-07-07
 */
@Service
@RequiredArgsConstructor
public class SampleService {

    private final IConfigService configService;

    public void test() {
        String organCode = "test";
        String configCode = "test";
        ConfigVal configObj = configService.queryObject(organCode, configCode, ConfigVal.class);
        List<ConfigVal> objList = configService.queryList(organCode, configCode, ConfigVal.class);
        Map<String, ConfigVal> configMap = configService.queryMap(organCode, configCode, ConfigVal.class);
        ConfigEntity configEntity = configService.queryEnable(organCode, configCode);
        String json = configService.queryJson(organCode, configCode);
    }

    public static class ConfigVal {

    }

}
