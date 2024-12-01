package com.fengwenyi.erwin.component.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fengwenyi.erwin.component.common.mybatis_plus.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Getter
@Setter
@ToString
@TableName("sys_user_permission")
public class SysUsePermissionEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 3842111884734461979L;

    private Long userId;

    private Long permissionId;

}
