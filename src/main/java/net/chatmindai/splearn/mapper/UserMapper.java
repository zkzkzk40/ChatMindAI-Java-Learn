package net.chatmindai.splearn.mapper;

import net.chatmindai.splearn.entity.user.dtos.UserQueryDto;
import net.chatmindai.splearn.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
/**
* @author 11375
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Mapper
* @createDate 2024-10-24 22:48:15
* @Entity net.chatmindai.splearn.entity.user.User
*/
public interface UserMapper extends BaseMapper<User> {
    User selectUserById(UserQueryDto dto);
}




