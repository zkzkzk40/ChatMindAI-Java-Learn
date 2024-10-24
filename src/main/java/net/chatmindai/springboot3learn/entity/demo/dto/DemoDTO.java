package net.chatmindai.springboot3learn.entity.demo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * demo参数
 *
 * @author zk
 * @date 2024/10/04
 */
@Data
public class DemoDTO implements Serializable {
    @Schema(description = "名称", example = "张三")
    @NotBlank(message = "名称不能为空")
    @Length(min = 2, max = 50, message = "名称长度必须在2到50个字符之间")
    private String name;

    @Schema(description = "年龄", example = "20")
    @Min(value = 0, message = "年龄必须大于或等于0")
    @Max(value = 150, message = "年龄必须小于或等于150")
    private int age;

    @Schema(description = "邮箱", example = "zhangsan@example.com")
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "手机号", example = "13800138000")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phoneNumber;

    @Schema(description = "生日", example = "2000-01-01")
    @Past(message = "生日必须是过去的日期")
    private LocalDate birthDate;

    @Schema(description = "计划日期", example = "2025-10-05")
    @Future(message = "计划日期必须是将来的日期")
    private LocalDate planDate;

    @Schema(description = "分数", example = "90.5")
    @Positive(message = "分数必须为正数")
    private double score;

    @Schema(description = "兴趣爱好列表", example = "[\"篮球\", \"足球\", \"游泳\", \"阅读\", \"编程\"]")
    @Size(min = 1, max = 5, message = "兴趣爱好列表必须包含1到5项")
    private List<String> hobbies;

    @Schema(description = "是否同意服务条款", example = "true")
    @AssertTrue(message = "必须同意服务条款")
    private boolean agreeTerms;
}