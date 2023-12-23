package com.fengwenyi.component.task.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-01
 */
@Data
public class TaskQueryDto {

    // 是否倒叙
    private Boolean sortDesc;

    // 每次执行任务数
    private Integer size;

    // 任务起始时间
    private LocalDateTime startDateTime; // 可以为空

    // 业务类型
    private String bizType;

}
