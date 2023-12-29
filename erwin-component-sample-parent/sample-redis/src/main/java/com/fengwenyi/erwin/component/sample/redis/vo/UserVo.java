package com.fengwenyi.erwin.component.sample.redis.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-26
 */
@Data
public class UserVo {

    private Long id;

    private String name;

    private LocalDateTime createDateTime;

}
