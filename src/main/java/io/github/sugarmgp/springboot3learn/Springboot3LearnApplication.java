package io.github.sugarmgp.springboot3learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.github.sugarmgp.springboot3learn.mapper")
public class Springboot3LearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot3LearnApplication.class, args);
    }

}
