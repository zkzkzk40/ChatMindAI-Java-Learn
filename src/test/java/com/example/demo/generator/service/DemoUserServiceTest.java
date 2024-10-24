package com.example.demo.generator.service;

import com.example.demo.domain.DemoUser;
import com.example.demo.mapper.DemoUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoUserServiceTest {
    @Autowired
    private DemoUserMapper userMapper;
    @Test
    public void ceshi(){
        for (DemoUser user:userMapper.selectList(null)){
            System.out.println(user.toString());
        }
    }
}