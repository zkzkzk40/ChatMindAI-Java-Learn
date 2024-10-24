package net.chatmindai.splearn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.chatmindai.splearn.mapper")
public class SpLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpLearnApplication.class, args);
    }

}

