package net.chatmindai.springboot3learn.service;

import net.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 23994
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service
* @createDate 2024-10-31 13:55:09
*/
public interface UserService extends IService<User> {

    List<User> getByName(String name);
}
