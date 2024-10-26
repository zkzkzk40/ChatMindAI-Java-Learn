package net.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.service.userService;
import net.springboot3learn.mapper.userMapper;
import org.springframework.stereotype.Service;

/**
* @author 瓜瓜乐
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-10-26 21:45:34
*/
@Service
public class userServiceImpl extends ServiceImpl<userMapper, User>
    implements userService{

}




