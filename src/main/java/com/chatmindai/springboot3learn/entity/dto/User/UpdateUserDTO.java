package com.chatmindai.springboot3learn.entity.dto.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author zzb
 */
@Data
public class UpdateUserDTO implements Serializable {
    @Schema(description = "id", example = "114514")
    private long id;

    @NotBlank(message = "名称不能为空")
    @Schema(description = "名称", example = "zzb")
    @Length(min = 2, max = 50, message = "名称长度必须在2到50个字符之间")
    private String name;

    @Schema(description = "年龄", example = "20")
    @Min(value = 0, message = "年龄必须大于或等于0")
    @Max(value = 150, message = "年龄必须小于或等于150")
    private int age;

    @NotNull(message = "邮箱不能为空")
    @Schema(description = "邮箱", example = "1234567890@qq.com")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "手机号", example = "13800000000")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phoneNumber;

    @Schema(description = "生日", example = "2024-01-19")
    @Past(message = "生日必须是过去的日期")
    private LocalDate birthDate;

    @Schema(description = "计划日期", example = "2024-12-19")
    @Future(message = "计划日期必须是将来的日期")
    private LocalDate planDate;

    @Schema(description = "分数", example = "99.9")
    @Positive(message = "分数必须为正数")
    private double score;

    @Schema(description = "兴趣爱好列表", example = "[\"basketball\",\"football\"]")
    @Size(min = 1, max = 5, message = "兴趣爱好列表必须包含1到5项")
    private List<String> hobbies;
}
