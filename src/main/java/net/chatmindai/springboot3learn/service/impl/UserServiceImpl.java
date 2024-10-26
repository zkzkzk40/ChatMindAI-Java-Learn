package net.chatmindai.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.chatmindai.springboot3learn.entity.user.user;
import net.chatmindai.springboot3learn.service.UserService;
import net.chatmindai.springboot3learn.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 瓜瓜乐
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-10-26 21:48:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, user>
    implements UserService {

}




