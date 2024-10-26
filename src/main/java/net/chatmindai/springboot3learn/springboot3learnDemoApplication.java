package net.chatmindai.springboot3learn;

@SpringBootApplication
@MapperScan("net.chatmindai.springboot3learn.mapper")
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class springboot3learnDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(springboot3learnDemoApplication.class, args);
    }
}
