package net.chatmindai.springboot3learn.mapper;

import net.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 86173
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Mapper
* @createDate 2024-10-24 19:40:42
* @Entity net.chatmindai.springboot3learn.entity.user.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserById(UserQueryDto dto);
    int updateById(User user);
}



