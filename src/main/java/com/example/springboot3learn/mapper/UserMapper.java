package com.example.springboot3learn.mapper;

import com.example.springboot3learn.entity.dto.UserQueryDto;
import com.example.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author stardawn
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Mapper
* @createDate 2024-10-28 23:52:53
* @Entity com.example.springboot3learn.entity.user.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserById(UserQueryDto dto);
}




