package com.bruce.springboot.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户表
 *
 * @TableName sys_user_basic
 */
@TableName(value = "sys_user_basic")
@Data
public class SysUserBasicEntity implements Serializable {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 性别(1:男,2:女)
     */
    @TableField(value = "sex")
    private Byte sex;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 公司名称
     */
    @TableField(value = "company_name")
    private String company_name;

    /**
     * 营业执照
     */
    @TableField(value = "license_url")
    private String license_url;

    /**
     * 积分
     */
    @TableField(value = "points")
    private Integer points;

    /**
     * 邀请用户uid
     */
    @TableField(value = "inviter_uid")
    private Integer inviter_uid;

    /**
     * 用户来源
     */
    @TableField(value = "source")
    private Byte source;

    /**
     * 状态 0已注册 1已加入 2已新建 3待审核 4已认证
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 添加时间
     */
    @TableField(value = "add_time")
    private Integer add_time;

    /**
     * 账号有效时间 0:永久
     */
    @TableField(value = "expire_time")
    private Integer expire_time;

    /**
     * 账号是否过期0:未过期1:已过期
     */
    @TableField(value = "is_expire")
    private Byte is_expire;

    /**
     * 隐私设置(1:对企业公开,2:完全保密)
     */
    @TableField(value = "is_open")
    private Byte is_open;

    /**
     * 最后登录时间
     */
    @TableField(value = "last_login")
    private Integer last_login;

    /**
     * 是否ci账号登录(0:否,1:是)
     */
    @TableField(value = "is_ci_login")
    private Byte is_ci_login;

    /**
     * 是否删除(0:否,1:是)
     */
    @TableField(value = "is_deleted")
    private Byte is_deleted;

    /**
     * 简历来源
     */
    @TableField(value = "profile_source")
    private Byte profile_source;

    /**
     * 是否开启个性化职位推荐
     */
    @TableField(value = "is_jd_recommend")
    private Boolean is_jd_recommend;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}