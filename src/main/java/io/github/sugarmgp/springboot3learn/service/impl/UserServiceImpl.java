package io.github.sugarmgp.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sugarmgp.springboot3learn.entity.user.User;
import io.github.sugarmgp.springboot3learn.mapper.UserMapper;
import io.github.sugarmgp.springboot3learn.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author SugarMGP
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-10-24 18:48:06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




