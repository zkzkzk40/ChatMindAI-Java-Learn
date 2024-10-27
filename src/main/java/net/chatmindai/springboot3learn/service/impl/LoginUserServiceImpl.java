package net.chatmindai.springboot3learn.service.impl;

import net.chatmindai.springboot3learn.entity.loginUser;
import net.chatmindai.springboot3learn.mapper.LoginUserMapper;
import net.chatmindai.springboot3learn.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    private LoginUserMapper loginuserMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public loginUser login(loginUser user) {
        //查询数据库
        loginUser userDB = loginuserMapper.login(user);
        if (userDB != null){
            return userDB;
        }
        throw new RuntimeException("登录失败");
    }
}