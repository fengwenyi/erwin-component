package com.fengwenyi.component.task.entity;

import com.fengwenyi.component.common.jpa.base.BaseEntity;
import com.fengwenyi.component.task.enums.TaskHandleResultEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-30
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "hc_task_log")
public class TaskLogEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -6644387873900456743L;

    @Column(name = "biz_type")
    private String bizType;

    @Column(name = "biz_id")
    private String bizId;

    @Column(name = "param")
    private String param;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private TaskHandleResultEnum result;

    @Column(name = "data")
    private String data;

}
