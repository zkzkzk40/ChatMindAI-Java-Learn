package net.chatmindai.springboot3learn.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

/**
 * 请求返回用户信息表
 * 屏蔽了密码（Email）字段
 * 添加了token字段
 */
@TableName(value ="demo_user",autoResultMap = true)
@Data
public class UserResponse implements Serializable {
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
    //指定数据库表中字段的映射方式
    //typeHandler 属性指定了使用的类型处理器，JacksonTypeHandler.class
    //这个处理器通常用于将 JSON 格式的数据与 Java 对象之间进行转换
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

    /**
     * 记录用户登录后返回的token
     */
    private String token;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public UserResponse(Long id, String name, Integer age, String phoneNumber, LocalDateTime birthDate, LocalDateTime planDate, Double score, List<String> hobbies, Integer agreeTerms, LocalDateTime createAt, LocalDateTime updateAt, String token) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.planDate = planDate;
        this.score = score;
        this.hobbies = hobbies;
        this.agreeTerms = agreeTerms;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.token = token;
    }
}