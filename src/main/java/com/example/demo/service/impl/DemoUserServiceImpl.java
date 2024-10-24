package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.DemoUser;
import com.example.demo.mapper.DemoUserMapper;
import com.example.demo.service.DemoUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class DemoUserServiceImpl extends ServiceImpl<DemoUserMapper, DemoUser>
    implements DemoUserService {
    @Autowired
    DemoUserMapper userDao;
    @Override
    public List<DemoUser> search(DemoUser user) {
        Long id = user.getId();
        String name = user.getName();
        List<DemoUser> search = userDao.search(id, name);
        for (DemoUser demoUser:search){
            System.out.println(demoUser.toString());
        }
        return search;
    }

    @Override
    public void delete(DemoUser user) {
        userDao.delete(user.getId(),user.getName());
    }

    @Override
    public void add(DemoUser user) {
        String name = user.getName();
        int age = user.getAge();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        LocalDate birthDate = user.getBirthDate();
        LocalDate planDate = user.getPlanDate();
        Double score = user.getScore();
        ObjectMapper objectMapper = new ObjectMapper();
        String hobby = "";
        List<Object> hobbies = user.getHobbies();
        try {
            hobby = objectMapper.writeValueAsString(hobbies);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // 处理异常
        }
        Boolean agreeTerms = user.getAgreeTerms();

        // 调用 userDao 的 add 方法
        userDao.add(name,age,email,phoneNumber,birthDate,planDate,score,hobby,agreeTerms);
    }

    @Override
    public void update(DemoUser user) {
        Long id = user.getId();
        String phoneNumber = user.getPhoneNumber();
        userDao.update(phoneNumber,id);
    }

}




