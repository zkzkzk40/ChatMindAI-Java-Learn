package com.example.Chatlearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.Chatlearn.mapper")
@SpringBootApplication
public class ChatlearnApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatlearnApplication.class, args);
    }

}
