package io.github.sugarmgp.springboot3learn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    public static <T> CommonResult<T> success(T data) {
        return success(data, "success");
    }

    public static <T> CommonResult<T> success(T data, String message) {
        return any(200, message, data);
    }

    public static <T> CommonResult<T> error(int code, String message) {
        return any(code, message, null);
    }

    public static <T> CommonResult<T> error(String message) {
        return error(500, message);
    }

    public static <T> CommonResult<T> any(int code, String message, T data) {
        return CommonResult.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
