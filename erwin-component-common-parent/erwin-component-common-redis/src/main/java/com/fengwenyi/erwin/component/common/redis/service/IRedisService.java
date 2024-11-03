package com.fengwenyi.erwin.component.common.redis.service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
public interface IRedisService {

    /**
     *
     * @param key
     * @param value
     * @param expireSeconds 单位：秒
     */
    void set(String key, String value, long expireSeconds);

    String get(String key);

    void deleteByKey(String key);

}
