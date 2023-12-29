package com.fengwenyi.erwin.component.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-14
 */
public class JacksonUtils {

    private static final JsonMapper jsonMapper = new JsonMapper();

    static {
        configure(jsonMapper);
    }

    public static void configure(JsonMapper jsonMapper) {
        // Include.NON_NULL 属性为NULL 不序列化
        //ALWAYS // 默认策略，任何情况都执行序列化
        //NON_EMPTY // null、集合数组等没有内容、空字符串等，都不会被序列化
        //NON_DEFAULT // 如果字段是默认值，就不会被序列化
        //NON_ABSENT // null的不会序列化，但如果类型是AtomicReference，依然会被序列化
        // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //允许字段名没有引号（可以进一步减小json体积）：
        jsonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //允许单引号：
        jsonMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // 允许出现特殊字符和转义符
        //mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);这个已经过时。
//        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);

        //允许C和C++样式注释：
        jsonMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        //序列化结果格式化，美化输出
        // mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //枚举输出成字符串
        //WRITE_ENUMS_USING_INDEX：输出索引
        jsonMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        //空对象不要抛出异常：
        jsonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        //Date、Calendar等序列化为时间格式的字符串(如果不执行以下设置，就会序列化成时间戳格式)：
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //反序列化时，遇到未知属性不要抛出异常：
        jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //反序列化时，遇到忽略属性不要抛出异常：
        jsonMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

        //反序列化时，空字符串对于的实例属性为null：
        jsonMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 采用字段，不使用 Getter
        jsonMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        jsonMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);

        jsonMapper.registerModule(new JavaTimeModule());
    }

    public static <T> T jsonObject(String json,  TypeReference<T> valueType) {
        try {
            return jsonMapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String json(T value) {
        try {
            return jsonMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonObject(String json,  Class<T> valueType) {
        try {
            return jsonMapper.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> jsonList(String json, Class<T> clazz) {
        try {
            CollectionType javaType = jsonMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return jsonMapper.readValue(json, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> jsonMap(String json) {
        TypeReference<Map<K, V>> typeReference = new TypeReference<Map<K, V>>(){};
        return jsonObject(json, typeReference);
    }

    public static <T> String jsonFormat(T value) {
        // 格式化输出
         jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
         return json(value);
    }

}