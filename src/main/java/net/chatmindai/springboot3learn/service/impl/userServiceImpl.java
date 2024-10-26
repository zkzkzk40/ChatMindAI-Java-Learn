package net.chatmindai.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.service.userService;
import net.chatmindai.springboot3learn.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author zhangkun
 * @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
 * @createDate 2024-10-08 14:41:15
 */
@Service
public class userServiceImpl extends ServiceImpl<UserMapper, User>
        implements userService {

}