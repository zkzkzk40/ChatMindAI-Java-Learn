package io.github.sugarmgp.springboot3learn.entity.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteUserDTO implements Serializable {
    @Schema(description = "ID", example = "1")
    private Long id;
}
