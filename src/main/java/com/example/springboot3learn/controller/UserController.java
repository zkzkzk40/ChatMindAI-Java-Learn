package com.example.springboot3learn.controller;

import com.example.springboot3learn.entity.dto.CommonResult;
import com.example.springboot3learn.entity.dto.DemoDTO;
import com.example.springboot3learn.entity.user.User;
import com.example.springboot3learn.entity.user.UserConverter;
import com.example.springboot3learn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关的 API")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "创建新用户", description = "接收 DemoDTO，转换为 User 并保存到数据库")
    public CommonResult<User> createUser(@Valid @RequestBody DemoDTO demoDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(demoDTO);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user, "用户创建成功");
        } else {
            return CommonResult.error("用户创建失败");
        }
    }

    @GetMapping("/search")
    @Operation(summary = "用户查询接口")
    public CommonResult<User> getUserById(@Validated @RequestParam Long id) {
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

    @PutMapping("/update/{id}")
    @Operation(summary = "用户修改接口")

    public CommonResult<User> updateUser(@PathVariable @RequestBody Long id, @Valid @RequestBody DemoDTO demoDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(demoDTO);
        user.setId(id); // 设置用户 ID，确保更新的是正确的用户


        boolean updated = userService.updateById(user);


        return updated ? CommonResult.success(user, "用户更新成功") : CommonResult.error("用户更新失败");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除接口")
    public CommonResult<String> deleteUser(@PathVariable @RequestParam  Long id) {
        boolean removed = userService.removeById(id);

        return removed ? CommonResult.success("用户删除成功") : CommonResult.error("用户删除失败");
    }

}
