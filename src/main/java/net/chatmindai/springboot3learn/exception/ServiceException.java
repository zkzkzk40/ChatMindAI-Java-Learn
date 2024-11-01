package net.chatmindai.springboot3learn.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {
    private final Integer code;

    public ServiceException(String message) {
        super(message);
        this.code = 500;
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
