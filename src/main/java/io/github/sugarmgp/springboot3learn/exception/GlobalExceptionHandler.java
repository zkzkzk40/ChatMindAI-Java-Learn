package io.github.sugarmgp.springboot3learn.exception;

import io.github.sugarmgp.springboot3learn.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResult<String>> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("发生未处理的异常", ex);
        CommonResult<String> result = CommonResult.error("发生未处理的异常: " + ex.getMessage());
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonResult<String>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("发生运行时异常", ex);

        CommonResult<String> result = CommonResult.error(
                HttpStatus.BAD_REQUEST.value(),
                "发生运行时异常: " + ex.getMessage()
        );

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResult<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        log.warn("参数校验失败", ex);

        CommonResult<Map<String, String>> result = CommonResult.error(
                HttpStatus.BAD_REQUEST.value(),
                "参数校验失败"
        );
        result.setData(errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
