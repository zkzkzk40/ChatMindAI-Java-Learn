package net.chatmindai.springboot3learn.service;

import net.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UserService extends IService<User> {

    boolean deleteById(long id);

    User findById(long id);
    boolean updateById(User user);
    boolean saveUser(User user);
}