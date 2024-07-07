package com.fengwenyi.erwin.component.config.util;

import com.fengwenyi.javalib.convert.JsonUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-19
 */
public class ConfigUtils {


    public static <T> T getObject(String json, Class<T> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        return JsonUtils.object(json, clazz);
    }

    public static <T> List<T> getList(String json, Class<T> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        return JsonUtils.collection(json, List.class, clazz);
    }

    public static <T> Map<String, T> getMap(String json, Class<T> clazz) {
        if (!StringUtils.hasText(json)) {
            return null;
        }
        return JsonUtils.map(json, String.class, clazz);
    }

}
