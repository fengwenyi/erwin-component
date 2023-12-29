package com.fengwenyi.erwin.component.sample.redis.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-26
 */
@Data
public class UserVo /*implements Serializable*/ {

//    @Serial
//    private static final long serialVersionUID = 2215423070276994378L;

    private Long id;

    private String name;

//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)		// 反序列化
//    @JsonSerialize(using = LocalDateTimeSerializer.class)		    // 序列化
    private LocalDateTime createDateTime;

}
