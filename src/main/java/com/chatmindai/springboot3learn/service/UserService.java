package com.chatmindai.springboot3learn.service;

import com.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

/**
* @author 10723
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service
* @createDate 2024-10-24 15:15:00
*/
public interface UserService extends IService<User> {

    @Cacheable(value = "users", key = "#id")
    User getById(Long id);
}
