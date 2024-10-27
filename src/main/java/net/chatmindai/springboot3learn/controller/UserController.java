package net.chatmindai.springboot3learn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.chatmindai.springboot3learn.annotation.LogInfo;
import net.chatmindai.springboot3learn.entity.CommonResult;
import net.chatmindai.springboot3learn.entity.demo.dto.User.CreateUserDTO;
import net.chatmindai.springboot3learn.entity.demo.dto.User.UpdateUserDTO;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.entity.user.UserConverter;
import net.chatmindai.springboot3learn.entity.loginUser;
import net.chatmindai.springboot3learn.service.UserService;
import net.chatmindai.springboot3learn.service.impl.LoginUserServiceImpl;
import net.chatmindai.springboot3learn.util.JwtUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关的 API")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final LoginUserServiceImpl loginUserServiceImp;

    @PostMapping("/user")
    @Operation(summary = "用户创建接口")
    @LogInfo("用户创建接口")
    public CommonResult<User> createUser(@Validated @RequestBody CreateUserDTO createUserDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(createUserDTO);
        return userService.save(user)
                ? CommonResult.success(user, "用户创建成功")
                : CommonResult.error("用户创建失败");
    }

    @DeleteMapping("/user")
    @Operation(summary = "用户删除接口")
    @LogInfo("用户删除接口")
    public CommonResult<Void> deleteUser(@Validated @RequestParam Long id) {
        return userService.removeById(id)
                ? CommonResult.success(null, "用户删除成功")
                : CommonResult.error("用户删除失败");
    }

    @PutMapping("/user")
    @Operation(summary = "用户修改接口")
    @LogInfo("用户修改接口")
    public CommonResult<User> updateUser(@Validated @RequestBody UpdateUserDTO updateUserDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(updateUserDTO);
        return userService.updateById(user)
                ? CommonResult.success(user, "更新用户成功")
                : CommonResult.error("更新用户失败");
    }

    @GetMapping("/user")
    @Operation(summary = "用户查询接口")
    @LogInfo("用户查询接口")
    public CommonResult<User> getUserByID(@Validated @RequestParam Long id) {
        try {
            User user = userService.getById(id);
            return CommonResult.success(user, user != null ? "用户查找成功" : "用户查找失败");
        } catch (Exception e) {
            log.error("查询失败: ", e);
            return CommonResult.error("查询失败: " + e.getMessage());
        }
    }

    @GetMapping("/user/login")
    @Operation(summary = "用户登录接口")
    @LogInfo("用户登录接口")
    public Map<String, Object> login(@RequestBody loginUser loginUser) {
        log.info("用户名：[{}]", loginUser.getUsername());
        log.info("密码：[{}]", loginUser.getPassword());
        Map<String, Object> response = new HashMap<>();

        try {
            loginUser userDB = loginUserServiceImp.login(loginUser);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", userDB.getId());
            payload.put("username", userDB.getUsername());
            String token = JwtUtil.getToken(payload);
            response.put("state", true);
            response.put("token", token);
            response.put("msg", "认证成功");
        } catch (Exception e) {
            log.error("登录失败: ", e);
            response.put("state", false);
            response.put("msg", e.getMessage());
        }
        return response;
    }

    @GetMapping("/user/logout")
    @Operation(summary = "用户登出接口")
    @LogInfo("用户登出接口")
    public CommonResult<String> logout() {
        return CommonResult.success("登出成功");
    }
}
