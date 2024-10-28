package net.chatmindai.springboot3learn.entity.user.dtos;
import lombok.Data;
import java.io.Serializable;

@Data
public class ExchangeUserDTO implements Serializable {
    private long id;

    private String name;

    private int age;

    private String phoneNumber;

}