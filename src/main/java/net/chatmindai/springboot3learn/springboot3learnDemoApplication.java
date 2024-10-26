package net.chatmindai.springboot3learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.chatmindai.springboot3learn.mapper")
public class springboot3learnDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(springboot3learnDemoApplication.class, args);
    }
}
