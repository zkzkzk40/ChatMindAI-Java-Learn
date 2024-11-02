package com.example.Chatlearn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.Chatlearn.entity.user.User;
import com.example.Chatlearn.entity.user.dtos.UserQueryDto;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DELL
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Mapper
* @createDate 2024-11-02 00:29:58
* @Entity iai.springboot3learn.entity.user.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserById(UserQueryDto dto);
}


