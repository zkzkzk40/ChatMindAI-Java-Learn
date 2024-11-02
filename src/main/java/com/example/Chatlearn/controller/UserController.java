package com.example.Chatlearn.controller;

import com.example.Chatlearn.entity.CommonResult;
import com.example.Chatlearn.entity.demo.dto.DemoDto;
import com.example.Chatlearn.entity.user.User;
import com.example.Chatlearn.entity.user.UserConverter;
import com.example.Chatlearn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关的 API")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "创建新用户", description = "接收 DemoDto，转换为 User 并保存到数据库")
    public CommonResult<User> createUser(@Valid @RequestBody DemoDto demoDTO) {
        User user = UserConverter.INSTANCE.userDtoToUser(demoDTO);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user, "用户创建成功");
        } else {
            return CommonResult.error("用户创建失败");
        }
    }
}
