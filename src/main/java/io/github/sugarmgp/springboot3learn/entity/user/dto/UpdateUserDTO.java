package io.github.sugarmgp.springboot3learn.entity.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserDTO implements Serializable {
    @Schema(description = "ID", example = "1")
    private long id;

    @Schema(description = "密码", example = "114514")
    private String password;

    @Schema(description = "邮箱", example = "1145141919@gmail.com")
    private String email;

    @Schema(description = "年龄", example = "18")
    private int age;
}
