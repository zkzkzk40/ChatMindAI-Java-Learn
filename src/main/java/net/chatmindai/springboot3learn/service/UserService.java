package net.chatmindai.springboot3learn.service;

import net.chatmindai.springboot3learn.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;
import net.chatmindai.springboot3learn.entity.user.dtos.UserLoginDto;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;
import org.mapstruct.control.MappingControl;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author 86173
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service
* @createDate 2024-10-26 01:20:12
*/
public interface UserService extends IService<User> {
       User getUserById(UserQueryDto dto);
       User login(UserLoginDto dto);
}
