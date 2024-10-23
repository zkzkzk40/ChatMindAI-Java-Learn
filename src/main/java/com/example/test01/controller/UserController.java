package com.example.test01.controller;

import com.example.test01.entity.CommonResult;
import com.example.test01.entity.Dto.User.CreateUserDTO;
import com.example.test01.entity.Dto.User.UpdateUserDTO;
import com.example.test01.entity.user.User;
import com.example.test01.entity.user.UserConverter;
import com.example.test01.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    @Operation(summary = "用户查询接口")
    public CommonResult<User> getUserByID(@Validated @RequestParam Long id) {
        log.info("入参为: {}", id);
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

    @PostMapping("/user")
    @Operation(summary = "用户创建接口")
    public CommonResult<User> createUser(@Validated @RequestBody CreateUserDTO createUserDTO) {
        log.info("入参为: {}", createUserDTO);

        User user = UserConverter.INSTANCE.userDtoToUser(createUserDTO);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user, "用户创建成功");
        }
        return CommonResult.error("用户创建失败");
    }

    @PutMapping("/user")
    @Operation(summary = "用户修改接口")
    public CommonResult<User> updateUser(@Validated @RequestBody UpdateUserDTO updateUserDTO) {
        log.info("入参为: {}", updateUserDTO);

        User user = UserConverter.INSTANCE.userDtoToUser(updateUserDTO);
        boolean saved = userService.updateById(user);
        if (saved) {
            return CommonResult.success(user, "更新用户成功");
        }
        return CommonResult.error("更新用户失败");
    }


    @DeleteMapping("/user")
    @Operation(summary = "用户删除接口")
    public CommonResult<User> deleteUser(@Validated @RequestParam Long id) {
        log.info("入参为: {}", id);

        boolean deleted = userService.removeById(id);
        if (deleted) {
            return CommonResult.success(null, "用户删除成功");
        }
        return CommonResult.error("用户删除失败");
    }
}