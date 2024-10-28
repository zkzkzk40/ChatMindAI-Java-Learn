package net.chatmindai.springboot3learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("net.chatmindai.springboot3learn.mapper")
@EnableCaching
public class Springboot3LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot3LearnApplication.class, args);
	}

}
