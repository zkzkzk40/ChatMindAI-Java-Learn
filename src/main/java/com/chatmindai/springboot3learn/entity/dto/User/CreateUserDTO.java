package com.chatmindai.springboot3learn.entity.dto.User;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * demo参数
 *
 * @author zzb
 */
@Data
public class CreateUserDTO implements Serializable {
    @NotBlank(message = "名称不能为空")
    @Length(min = 2, max = 50, message = "名称长度必须在2到50个字符之间")
    private String name;

    @Min(value = 0, message = "年龄必须大于或等于0")
    @Max(value = 150, message = "年龄必须小于或等于150")
    private int age;

    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phoneNumber;

    @Past(message = "生日必须是过去的日期")
    private LocalDate birthDate;

    @Future(message = "计划日期必须是将来的日期")
    private LocalDate planDate;

    @Positive(message = "分数必须为正数")
    private double score;

    @Size(min = 1, max = 5, message = "兴趣爱好列表必须包含1到5项")
    private List<String> hobbies;

    @AssertTrue(message = "必须同意服务条款")
    private boolean agreeTerms;
}