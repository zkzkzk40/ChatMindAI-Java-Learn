package net.chatmindai.springboot3learn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserById(UserQueryDto dto);
}




