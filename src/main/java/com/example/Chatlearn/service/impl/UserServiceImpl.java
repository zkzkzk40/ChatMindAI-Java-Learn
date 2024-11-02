package com.example.Chatlearn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.Chatlearn.entity.user.User ;
import com.example.Chatlearn.service.UserService;
import com.example.Chatlearn.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-11-02 00:29:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




