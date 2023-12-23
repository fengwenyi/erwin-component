package com.fengwenyi.component.task.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fengwenyi.component.common.jpa.base.BaseBizEntity;
import com.fengwenyi.component.task.enums.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-30
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "hc_task")
public class TaskEntity extends BaseBizEntity {

    @Serial
    private static final long serialVersionUID = -2632495378211863219L;

    // 业务ID
    @Column(name = "biz_id")
    private String bizId;

    // 业务类型
    @Column(name = "biz_type")
    private String bizType;

    // 参数
    @Column(name = "param")
    private String param;

    // 任务状态
    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum taskStatus;

    // 重试次数
    @Column(name = "retry_count")
    private Integer retryCount;

    // 计划执行时间
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "plan_execute_date_time")
    private LocalDateTime planExecuteDateTime;

}
