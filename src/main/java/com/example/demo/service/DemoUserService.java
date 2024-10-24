package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.DemoUser;

import java.util.List;

/**
* @author 28365
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service
* @createDate 2024-10-23 22:15:42
*/
public interface DemoUserService extends IService<DemoUser> {
    public List<DemoUser> search(DemoUser user);
    void delete(DemoUser uer);
    void add(DemoUser user);
    void update(DemoUser user);
}
