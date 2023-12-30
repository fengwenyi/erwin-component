package com.fengwenyi.erwin.component.common.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fengwenyi.erwin.component.common.util.JacksonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-26
 */
//@Configuration
public class ComponentRedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // 使用StringRedisSerializer来序列化和反序列化键（key）
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        // 使用GenericJackson2JsonRedisSerializer来序列化和反序列化值（value）
        template.setValueSerializer(redisSerializer());
        template.setHashValueSerializer(redisSerializer());
        template.afterPropertiesSet();

        return template;
    }

@Bean
public RedisCacheConfiguration redisCacheConfiguration() {
    return RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(
                    RedisSerializationContext
                            .SerializationPair
//                            .fromSerializer(RedisSerializer.json())
//                            .fromSerializer(
//                                    new GenericJackson2JsonRedisSerializer()
//                            )
                            .fromSerializer(redisSerializer())
            );
}

private RedisSerializer<Object> redisSerializer() {
    JsonMapper jsonMapper = new JsonMapper();
    JacksonUtils.configure(jsonMapper);
    jsonMapper.activateDefaultTyping(
            LaissezFaireSubTypeValidator.instance,
            ObjectMapper.DefaultTyping.NON_FINAL
    );
    return new GenericJackson2JsonRedisSerializer(jsonMapper);
}
}
