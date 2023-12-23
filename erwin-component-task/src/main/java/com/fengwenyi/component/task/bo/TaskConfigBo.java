package com.fengwenyi.component.task.bo;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-01
 */
@Data
public class TaskConfigBo {

    // 业务类型
    private String bizType;

    // 业务实现的bean
    private String beanHandler;

    // 最大重试次数
    private Integer maxRetryCount;

    // 任务间隔时间配置（分钟）
    private List<Integer> intervalMinutes;

    // 不保存返回数据
    private Boolean notSaveData;

}
