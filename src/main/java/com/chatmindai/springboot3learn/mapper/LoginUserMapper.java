package com.chatmindai.springboot3learn.mapper;

import com.chatmindai.springboot3learn.entity.loginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginUserMapper {
    loginUser login(loginUser user);
}
