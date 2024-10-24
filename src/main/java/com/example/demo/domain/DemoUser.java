package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 演示用户信息表
 * @TableName demo_user
 */
@TableName(value ="demo_user")
@Data
public class DemoUser implements Serializable {
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
    private LocalDate birthDate;

    /**
     * 计划日期，必须是未来的日期
     */
    private LocalDate planDate;

    /**
     * 用户分数，必须为正数
     */
    private Double score;

    /**
     * 用户兴趣爱好列表，1-5项
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Object> hobbies;

    /**
     * 是否同意服务条款
     */
    private Boolean agreeTerms;

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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DemoUser other = (DemoUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getBirthDate() == null ? other.getBirthDate() == null : this.getBirthDate().equals(other.getBirthDate()))
            && (this.getPlanDate() == null ? other.getPlanDate() == null : this.getPlanDate().equals(other.getPlanDate()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getHobbies() == null ? other.getHobbies() == null : this.getHobbies().equals(other.getHobbies()))
            && (this.getAgreeTerms() == null ? other.getAgreeTerms() == null : this.getAgreeTerms().equals(other.getAgreeTerms()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getBirthDate() == null) ? 0 : getBirthDate().hashCode());
        result = prime * result + ((getPlanDate() == null) ? 0 : getPlanDate().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getHobbies() == null) ? 0 : getHobbies().hashCode());
        result = prime * result + ((getAgreeTerms() == null) ? 0 : getAgreeTerms().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", email=").append(email);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", planDate=").append(planDate);
        sb.append(", score=").append(score);
        sb.append(", hobbies=").append(hobbies);
        sb.append(", agreeTerms=").append(agreeTerms);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}