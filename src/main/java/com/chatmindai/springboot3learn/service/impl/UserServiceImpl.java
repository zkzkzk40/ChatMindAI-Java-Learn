package com.chatmindai.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatmindai.springboot3learn.entity.user.User;
import com.chatmindai.springboot3learn.service.UserService;
import com.chatmindai.springboot3learn.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 10723
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-10-24 15:15:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




