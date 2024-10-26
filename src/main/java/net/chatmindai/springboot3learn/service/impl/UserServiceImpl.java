package net.chatmindai.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.entity.user.dtos.UserLoginDto;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;
import net.chatmindai.springboot3learn.service.UserService;
import net.chatmindai.springboot3learn.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 86173
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-10-26 01:20:12
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Override
    public User getUserById(UserQueryDto dto) {
        return baseMapper.selectUserById(dto);
    }
    @Override
    public User login(UserLoginDto dto) {
        return baseMapper.login(dto);
    }
}




