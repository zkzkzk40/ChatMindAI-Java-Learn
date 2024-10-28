package net.chatmindai.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.service.UserService;
import net.chatmindai.springboot3learn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @author 760
 */

//    @Override
//    public boolean deleteById(long id) {
//        return this.removeById(id);
//    }
//
//    @Override
//    public User findById(long id) {
//        return this.getById(id);
//    }
//
//    @Override
//    public boolean updateById(User user) {
//
//        return this.updateById(user);
//    }
//
//    @Override
//    public boolean saveUser(User user) {
//
//        return this.save(user);
//    }

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @CacheEvict(value = "userCache", key = "#id")
    public boolean deleteById(long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public User findById(long id) {
        return userMapper.selectById(id);
    }

    @Override
    @CachePut(value = "userCache", key = "#user.id")
    public boolean updateById(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.insert(user) > 0;
    }
}