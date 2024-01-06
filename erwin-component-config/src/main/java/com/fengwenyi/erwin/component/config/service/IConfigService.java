package com.fengwenyi.erwin.component.config.service;

import com.fengwenyi.erwin.component.config.entity.ConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-19
 */
public interface IConfigService {

    String queryJson(String organCode, String configCode);

    ConfigEntity queryEnable(String organCode, String configCode);

    <T> T queryObject(String organCode, String configCode, Class<T> clazz);

    <T> List<T> queryList(String organCode, String configCode, Class<T> clazz);

    <T> Map<String, T> queryMap(String organCode, String configCode);

}
