package com.fengwenyi.erwin.component.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fengwenyi.erwin.component.common.mybatis_plus.base.BaseBizEntity;
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
@TableName("sys_role")
public class SysRoleEntity extends BaseBizEntity {

    @Serial
    private static final long serialVersionUID = 2458122357260711246L;

    @TableField("role_label")
    private String roleLabel;

    @TableField("role_code")
    private String roleCode;

    /** 排序值，从大到小 */
    @TableField("sort_value")
    private Double sortValue;
}
