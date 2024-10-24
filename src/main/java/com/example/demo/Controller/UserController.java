package com.example.demo.Controller;

import com.example.demo.domain.DemoUser;
import com.example.demo.service.DemoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    DemoUserService service;
    @RequestMapping("/search")
    public List<DemoUser> searchUser(@Validated @RequestBody DemoUser user){
        return service.search(user);
    }

    @RequestMapping("/delete")
    public void delete(@Validated @RequestBody DemoUser user){
        service.delete(user);
    }

    @RequestMapping("/add")
    public void add(@Validated @RequestBody DemoUser user){
        service.add(user);
    }

    @RequestMapping("/updatephonenumber")
    public void update(@Validated @RequestBody DemoUser user){
        service.update(user);
    }
}
