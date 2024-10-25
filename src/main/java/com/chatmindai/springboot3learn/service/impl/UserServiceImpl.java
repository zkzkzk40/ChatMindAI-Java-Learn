package com.chatmindai.springboot3learn.service.impl;

import com.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatmindai.springboot3learn.mapper.UserMapper;
import com.chatmindai.springboot3learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "users", key = "#id")
    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }
}