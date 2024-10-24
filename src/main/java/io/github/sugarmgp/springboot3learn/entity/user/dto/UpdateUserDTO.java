package io.github.sugarmgp.springboot3learn.entity.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class UpdateUserDTO implements Serializable {
    @Schema(description = "id", example = "1")
    private long id;

    @Schema(description = "密码", example = "114514")
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 32, message = "密码长度必须在 8 到 32 个字符之间")
    private String password;

    @Schema(description = "邮箱", example = "11451418@qq.com")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "年龄", example = "18")
    @Min(value = 1, message = "年龄必须大于等于 1")
    @Max(value = 100, message = "年龄必须小于等于 100")
    private int age;
}
