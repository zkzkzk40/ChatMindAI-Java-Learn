package com.example.test01.controller;

import com.example.test01.entity.CommonResult;
import com.example.test01.entity.Dto.User.CreateUserDTO;
import com.example.test01.entity.Dto.User.DeleteUserDTO;
import com.example.test01.entity.Dto.User.QueryUserDTO;
import com.example.test01.entity.Dto.User.UpdateUserDTO;
import com.example.test01.entity.user.User;
import com.example.test01.entity.user.UserConverter;
import com.example.test01.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public CommonResult<User> getUserByID(@Validated @RequestBody QueryUserDTO queryUserDTO) {
        try {
            User user = userService.getById(queryUserDTO.getId());
            if (user == null) {
                return CommonResult.success(null, "用户查找失败");
            }
            return CommonResult.success(user, "用户查找成功");
        } catch (Exception e) {
            return CommonResult.error("查询失败: " + e.getMessage()); // 处理其他异常
        }
    }

    @PostMapping("/user")
    public CommonResult<User> createUser(@Validated @RequestBody CreateUserDTO createUserDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(createUserDTO);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user, "用户创建成功");
        }
        return CommonResult.error("用户创建失败");
    }

    @PutMapping("/user")
    public CommonResult<User> updateUser(@Validated @RequestBody UpdateUserDTO updateUserDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(updateUserDTO);
        boolean saved = userService.updateById(user);
        if (saved) {
            return CommonResult.success(user, "更新用户成功");
        }
        return CommonResult.error("更新用户失败");
    }


    @DeleteMapping("/user")
    public CommonResult<User> deleteUser(@Validated @RequestBody DeleteUserDTO deleteUserDTO) {
        boolean deleted = userService.removeById(deleteUserDTO.getId());
        if (deleted) {
            return CommonResult.success(null, "用户删除成功");
        }
        return CommonResult.error("用户删除失败");
    }
}