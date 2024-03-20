package com.fengwenyi.erwin.component.common.mybatis_plus.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-13
 */
@Getter
@Setter
@ToString
public class BaseBizEntity extends BaseEntity {

    private static final long serialVersionUID = -4713300738290113747L;

    @TableField("status")
    private Boolean status;

}
