package com.chatmindai.springboot3learn.controller;

import com.chatmindai.springboot3learn.entity.CommonResult;
import com.chatmindai.springboot3learn.entity.dto.User.CreateUserDTO;
import com.chatmindai.springboot3learn.entity.dto.User.UpdateUserDTO;
import com.chatmindai.springboot3learn.entity.loginUser;
import com.chatmindai.springboot3learn.entity.user.User;
import com.chatmindai.springboot3learn.entity.user.UserConverter;
import com.chatmindai.springboot3learn.service.LoginUserService;
import com.chatmindai.springboot3learn.service.UserService;
import com.chatmindai.springboot3learn.annotation.LogInfo;
import com.chatmindai.springboot3learn.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping("/user")
    @Operation(summary = "用户创建接口")
    @LogInfo("用户创建接口")
    public CommonResult<User> createUser(@Validated @RequestBody CreateUserDTO createUserDTO) {

        User user = UserConverter.INSTANCE.userDtoToUser(createUserDTO);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user, "用户创建成功");
        }
        return CommonResult.error("用户创建失败");
    }

    @DeleteMapping("/user")
    @Operation(summary = "用户删除接口")
    @LogInfo("用户删除接口")
    public CommonResult<User> deleteUser(@Validated @RequestParam Long id) {

        boolean deleted = userService.removeById(id);
        if (deleted) {
            return CommonResult.success(null, "用户删除成功");
        }
        return CommonResult.error("用户删除失败");
    }

    @PutMapping("/user")
    @Operation(summary = "用户修改接口")
    @LogInfo("用户修改接口")
    public CommonResult<User> updateUser(@Validated @RequestBody UpdateUserDTO updateUserDTO) {

        User user = UserConverter.INSTANCE.userDtoToUser(updateUserDTO);
        boolean saved = userService.updateById(user);
        if (saved) {
            return CommonResult.success(user, "更新用户成功");
        }
        return CommonResult.error("更新用户失败");
    }

    @GetMapping("/user")
    @Operation(summary = "用户查询接口")
    @LogInfo("用户查询接口")
    public CommonResult<User> getUserByID(@Validated @RequestParam Long id) {
        try {
            User user = userService.getById(id);
            if (user == null) {
                return CommonResult.success(null, "用户查找失败");
            }
            return CommonResult.success(user, "用户查找成功");
        } catch (Exception e) {
            return CommonResult.error("查询失败: " + e.getMessage()); // 处理其他异常
        }
    }

    @Autowired
    private LoginUserService loginUserService;

    @GetMapping("/user/login")
    @Operation(summary = "用户登录接口")
    @LogInfo("用户登录接口")
    public Map<String,Object> login(loginUser loginUser){
        log.info("用户名：[{}]",loginUser.getUsername());
        log.info("密码：[{}]",loginUser.getPassword());

        Map<String, Object> map = new HashMap<>();

        try {
            loginUser userDB = loginUserService.login(loginUser);
            Map<String,String> payload = new HashMap<>();
            payload.put("id",userDB.getId());
            payload.put("username",userDB.getUsername());
            //生成JWT令牌
            String token = JwtUtil.getToken(payload);
            map.put("state",true);
            map.put("token",token);
            map.put("msg","认证成功");
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
