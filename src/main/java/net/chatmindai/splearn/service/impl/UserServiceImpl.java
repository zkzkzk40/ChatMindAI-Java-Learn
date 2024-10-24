package net.chatmindai.splearn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.chatmindai.splearn.entity.user.User;
import net.chatmindai.splearn.service.UserService;
import net.chatmindai.splearn.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 11375
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-10-24 22:48:15
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




