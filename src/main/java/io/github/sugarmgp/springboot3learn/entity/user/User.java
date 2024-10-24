package io.github.sugarmgp.springboot3learn.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName user
 */
@TableName(value = "user", autoResultMap = true)
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}