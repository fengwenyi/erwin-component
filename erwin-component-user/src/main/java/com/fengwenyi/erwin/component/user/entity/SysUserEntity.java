package com.fengwenyi.erwin.component.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fengwenyi.erwin.component.common.mybatis_plus.base.BaseBizEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * 用户表
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Getter
@Setter
@ToString
@TableName("sys_user")
public class SysUserEntity extends BaseBizEntity {

    @Serial
    private static final long serialVersionUID = 7956245242779170013L;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("locked")
    private Boolean locked;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    /**
     * 渠道
     */
    @TableField("channel_code")
    private String channelCode;

    @TableField("avatar_url")
    private String avatarUrl;

}
