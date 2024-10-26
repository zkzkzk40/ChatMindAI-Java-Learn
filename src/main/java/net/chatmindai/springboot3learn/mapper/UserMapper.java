package net.chatmindai.springboot3learn.mapper;

import net.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.chatmindai.springboot3learn.entity.user.dtos.UserLoginDto;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;

/**
* @author 86173
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Mapper
* @createDate 2024-10-26 01:20:12
* @Entity net.chatmindai.springboot3learn.entity.user.User
*/
public interface UserMapper extends BaseMapper<User> {
    User selectUserById(UserQueryDto dto);
    User login(UserLoginDto dto);
}




