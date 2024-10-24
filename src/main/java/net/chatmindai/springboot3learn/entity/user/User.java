package net.chatmindai.springboot3learn.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 演示用户信息表
 * @TableName demo_user
 */
@TableName(value ="demo_user",autoResultMap = true)
@Data
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
     * 用户邮箱地址
     */
    private String email;

    /**
     * 用户手机号，格式：1开头的11位数字
     */
    private String phoneNumber;

    /**
     * 用户出生日期
     */
    private LocalDateTime birthDate;

    /**
     * 计划日期，必须是未来的日期
     */
    private LocalDateTime planDate;

    /**
     * 用户分数，必须为正数
     */
    private Double score;

    /**
     * 用户兴趣爱好列表，1-5项
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> hobbies;

    /**
     * 是否同意服务条款
     */
    private Integer agreeTerms;

    /**
     * 记录创建时间
     */
    private LocalDateTime createAt;

    /**
     * 记录最后更新时间
     */
    private LocalDateTime updateAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
