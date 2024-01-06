package com.fengwenyi.erwin.component.config.util;

import info.hxgy.component.common.util.JacksonUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-19
 */
public class ConfigUtils {


    public static <T> T getObject(String configJson, Class<T> clazz) {
        if (!StringUtils.hasText(configJson)) {
            return null;
        }
        return JacksonUtils.jsonObject(configJson, clazz);
    }

    public static <T> List<T> getList(String configJson, Class<T> clazz) {
        if (!StringUtils.hasText(configJson)) {
            return null;
        }

       return JacksonUtils.jsonList(configJson, clazz);
    }

    public static <T> Map<String, T> getMap(String configJson) {
        if (!StringUtils.hasText(configJson)) {
            return null;
        }
        return JacksonUtils.jsonMap(configJson);
    }

}
