package net.chatmindai.splearn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.chatmindai.splearn.entity.CommonResult;
import net.chatmindai.splearn.entity.demo.dto.DemoDTO;
import net.chatmindai.splearn.entity.user.User;
import net.chatmindai.splearn.entity.user.UserConverter;
import net.chatmindai.splearn.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
