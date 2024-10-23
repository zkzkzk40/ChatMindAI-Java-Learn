package com.example.test01.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * 演示用户信息表
 * @TableName user
 */
@TableName(value ="user")
public class User implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名称，长度2-50个字符
     */
    private String name;

    /**
     * 用户年龄，0-150岁
     */
    private Integer age;

    /**
     * 用户手机号，格式：1开头的11位数字
     */
    private String phoneNumber;

    /**
     * 记录创建时间
     */
    @JsonIgnore
    private Date createAt;

    /**
     * 记录最后更新时间
     */
    @JsonIgnore
    private Date updateAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户名称，长度2-50个字符
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名称，长度2-50个字符
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 用户年龄，0-150岁
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 用户年龄，0-150岁
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 用户手机号，格式：1开头的11位数字
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 用户手机号，格式：1开头的11位数字
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 记录创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 记录创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 记录最后更新时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 记录最后更新时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}