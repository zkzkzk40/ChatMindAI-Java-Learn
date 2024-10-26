package net.chatmindai.springboot3learn.exception;

import net.chatmindai.springboot3learn.entity.CommonResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理器
 * 用于统一处理应用中抛出的异常，并返回标准化的错误响应
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理所有未被特定处理器捕获的异常
     *
     * @param ex 捕获到的异常
     * @param request 当前的web请求
     * @return 包含错误信息的ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResult<String>> handleAllExceptions(Exception ex, WebRequest request) {
        // 记录异常日志
        log.error("发生未处理的异常", ex);

        // 创建错误响应
        CommonResult<String> result = CommonResult.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "发生未处理的异常: " + ex.getMessage()
        );

        // 返回HTTP 500 内部服务器错误状态码
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理所有RuntimeException及其子类的异常
     *
     * @param ex 捕获到的RuntimeException
     * @param request 当前的web请求
     * @return 包含错误信息的ResponseEntity
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonResult<String>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        // 记录运行时异常日志
        log.error("发生运行时异常", ex);

        // 创建错误响应
        CommonResult<String> result = CommonResult.error(
                HttpStatus.BAD_REQUEST.value(),
                "发生运行时异常: " + ex.getMessage()
        );

        // 返回HTTP 400 错误请求状态码
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
