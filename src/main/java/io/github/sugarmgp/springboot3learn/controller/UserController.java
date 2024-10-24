package io.github.sugarmgp.springboot3learn.controller;

import io.github.sugarmgp.springboot3learn.entity.CommonResult;
import io.github.sugarmgp.springboot3learn.entity.user.User;
import io.github.sugarmgp.springboot3learn.entity.user.UserConverter;
import io.github.sugarmgp.springboot3learn.entity.user.dto.CreateUserDTO;
import io.github.sugarmgp.springboot3learn.entity.user.dto.UpdateUserDTO;
import io.github.sugarmgp.springboot3learn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "用户控制器", description = "用户控制器")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "获取用户信息")
    @GetMapping("/user")
    public Object getUserById(@Validated @RequestParam long id) {
        User user = userService.getById(id);
        if (user == null) {
            return CommonResult.error("用户查找失败");
        }
        return CommonResult.success(user);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/user")
    public Object deleteUser(@Validated @RequestBody long id) {
        boolean deleted = userService.removeById(id);
        if (deleted) {
            return CommonResult.success(null);
        }
        return CommonResult.error("用户删除失败");
    }

    @Operation(summary = "更新用户信息")
    @PutMapping("/user")
    public Object updateUser(@Validated @RequestBody UpdateUserDTO updateUserDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(updateUserDTO);
        boolean saved = userService.updateById(user);
        if (saved) {
            return CommonResult.success(user);
        }
        return CommonResult.error("更新用户失败");
    }

    @Operation(summary = "新建用户")
    @PostMapping("/user")
    public Object createUser(@Validated @RequestBody CreateUserDTO createUserDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(createUserDTO);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user);
        }
        return CommonResult.error("用户创建失败");
    }
}
