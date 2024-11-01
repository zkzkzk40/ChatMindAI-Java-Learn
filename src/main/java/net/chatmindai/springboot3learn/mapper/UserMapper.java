package net.chatmindai.springboot3learn.mapper;

import net.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 23994
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Mapper
* @createDate 2024-10-31 13:55:09
* @Entity net.chatmindai.springboot3learn.entity.user.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<User> selectByname(@Param("name") String name);

    User selectUserById(UserQueryDto dto);
}




