package com.chatmindai.springboot3learn.controller;

import com.chatmindai.springboot3learn.entity.CommonResult;
import com.chatmindai.springboot3learn.entity.dto.User.CreateUserDTO;
import com.chatmindai.springboot3learn.entity.dto.User.UpdateUserDTO;
import com.chatmindai.springboot3learn.entity.user.User;
import com.chatmindai.springboot3learn.entity.user.UserConverter;
import com.chatmindai.springboot3learn.service.UserService;
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

    @PostMapping("/user")
    @Operation(summary = "用户创建接口")
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
    public CommonResult<User> deleteUser(@Validated @RequestParam Long id) {

        boolean deleted = userService.removeById(id);
        if (deleted) {
            return CommonResult.success(null, "用户删除成功");
        }
        return CommonResult.error("用户删除失败");
    }

    @PutMapping("/user")
    @Operation(summary = "用户修改接口")
    public CommonResult<User> updateUser(@Validated @RequestBody UpdateUserDTO updateUserDTO) {

        User user = UserConverter.INSTANCE.userDtoToUser(updateUserDTO);
        boolean saved = userService.updateById(user);
        if (saved) {
            return CommonResult.success(user, "更新用户成功");
        }
        return CommonResult.error("更新用户失败");
    }

}
