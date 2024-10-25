package net.chatmindai.springboot3learn.service;

import net.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;

/**
* @author 86173
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service
* @createDate 2024-10-24 19:40:42
*/
public interface UserService extends IService<User> {

        /**
        * 根据用户ID查询用户信息
        *
        * @param dto 用户ID
        * @return 用户信息
        */
        User getUserById(UserQueryDto dto);

        /**
         * 更新用户信息
         * @param user
         * @return
         */
        boolean updateUser(User user);

}
