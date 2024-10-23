package io.github.sugarmgp.springboot3learn.controller;

import io.github.sugarmgp.springboot3learn.entity.user.dto.CreateUserDTO;
import io.github.sugarmgp.springboot3learn.entity.user.dto.DeleteUserDTO;
import io.github.sugarmgp.springboot3learn.entity.user.dto.GetUserDTO;
import io.github.sugarmgp.springboot3learn.entity.user.dto.UpdateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "用户控制器", description = "用户控制器")
@RestController
@RequestMapping("/api")
public class UserController {

    @Operation(summary = "获取用户信息")
    @GetMapping("/user")
    public Object getUser(@Validated @RequestBody GetUserDTO getUserDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Hello World");
        return map;
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/user")
    public Object deleteUser(@Validated @RequestBody DeleteUserDTO deleteUserDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Hello World");
        return map;
    }

    @Operation(summary = "更新用户信息")
    @PutMapping("/user")
    public Object updateUser(@Validated @RequestBody UpdateUserDTO updateUserDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Hello World");
        return map;
    }

    @Operation(summary = "新建用户")
    @PostMapping("/user")
    public Object createUser(@Validated @RequestBody CreateUserDTO createUserDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Hello World");
        return map;
    }
}
