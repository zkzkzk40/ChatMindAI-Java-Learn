package net.chatmindai.springboot3learn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.chatmindai.springboot3learn.entity.CommonResult;
import net.chatmindai.springboot3learn.entity.demo.dto.DemoDTO;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.entity.user.UserConverter;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;
import net.chatmindai.springboot3learn.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.io.Serializable;

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
    @PostMapping("/getById")
    @Operation(summary = "根据ID查询用户", description = "根据用户ID查询用户信息")
    public CommonResult<User> getUserById(@RequestBody UserQueryDto dto) {
        User user = userService.getUserById(dto);
        if (user != null) {
            return CommonResult.success(user, "用户查询成功");
        } else {
            return CommonResult.error("用户不存在");
        }
    }

    @PostMapping("/update")
    @Operation(summary = "更新用户信息", description = "根据传入的用户信息更新用户")
    public CommonResult<User> updateUser(@RequestBody User user) {
//        User user = UserConverter.INSTANCE.userDtoToUser(demoDTO);

        System.out.println("Received user: " + user); // 输出接收到的用户数据
        if (user == null) {
            return CommonResult.error("传入的用户数据不能为空");
        }
        boolean updated = userService.updateUser(user);
        if (updated) {
            return CommonResult.success(user, "用户更新成功");
        } else {
            return CommonResult.error("用户更新失败");
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "删除用户", description = "根据用户ID删除用户")
    public CommonResult<String> deleteUser(@RequestBody UserQueryDto dto) {
        long id = dto.getId();
        boolean deleted = userService.removeById(id);
        if (deleted) {
            return CommonResult.success("用户删除成功");
        } else {
            return CommonResult.error("用户删除失败");
        }
    }

}