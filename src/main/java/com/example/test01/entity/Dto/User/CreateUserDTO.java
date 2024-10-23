package com.example.test01.entity.Dto.User;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @author MangoGovo
 */
@Data
public class CreateUserDTO implements Serializable {
    @NotBlank(message = "名称不能为空")
    @Schema(description = "名称", example = "MangoGovo")
    @Length(min = 2, max = 50, message = "名称长度必须在2到50个字符之间")
    private String name;

    @Schema(description = "年龄", example = "20")
    @Min(value = 0, message = "年龄必须大于或等于0")
    @Max(value = 150, message = "年龄必须小于或等于150")
    private int age;

    @Schema(description = "手机号", example = "13333333333")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phoneNumber;

}