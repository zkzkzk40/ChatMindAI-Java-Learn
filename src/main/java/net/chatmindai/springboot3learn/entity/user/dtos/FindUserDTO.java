package net.chatmindai.springboot3learn.entity.user.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


@Data
public class FindUserDTO implements Serializable {
    @Schema(description = "id")
    private long id;


}