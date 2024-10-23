package com.example.test01.entity.Dto.User;


import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author MangoGovo
 */
@Data
public class UpdateUserDTO implements Serializable {
    private long id;

    private String name;

    private int age;

    private String phoneNumber;

}