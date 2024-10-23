package com.example.test01.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test01.entity.user.User;
import com.example.test01.service.UserService;
import com.example.test01.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author mangogo
* @description 针对表【user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-10-23 08:04:15
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




