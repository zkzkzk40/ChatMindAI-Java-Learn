package net.chatmindai.springboot3learn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.chatmindai.springboot3learn.entity.CommonResult;
import net.chatmindai.springboot3learn.entity.demo.dto.DemoDTO;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.entity.user.UserConverter;
import net.chatmindai.springboot3learn.entity.user.dtos.AddUserDTO;
import net.chatmindai.springboot3learn.entity.user.dtos.DeleteUserDTO;
import net.chatmindai.springboot3learn.entity.user.dtos.ExchangeUserDTO;
import net.chatmindai.springboot3learn.entity.user.dtos.FindUserDTO;
import net.chatmindai.springboot3learn.service.UserService;
import org.springframework.cache.annotation.Cacheable;
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

//    @PostMapping("/add")
//    public CommonResult<User> addUser(@Valid @RequestBody AddUserDTO addUserDTO) {
//        User user = UserConverter.INSTANCE.addUserDtoToUser(addUserDTO);
//        boolean saved = userService.save(user);
//        if (saved) {
//            return CommonResult.success(user, "用户创建成功");
//        } else {
//            return CommonResult.error("用户创建失败");
//        }
//    }
//
//    @PostMapping("/find")
//    public CommonResult<User> findUser(@Valid @RequestBody FindUserDTO findUserDTO) {
//        User user = userService.findById(findUserDTO.getId());
//        if (user != null) {
//            return CommonResult.success(user, "用户查找成功");
//        } else {
//            return CommonResult.error("用户查找失败");
//        }
//    }
//
//    @PostMapping("/delete")
//    public CommonResult<User> deleteUser(@Valid @RequestBody DeleteUserDTO deleteUserDTO) {
//        boolean deleted = userService.deleteById(deleteUserDTO.getId());
//        if (deleted) {
//            return CommonResult.success(null, "用户删除成功");
//        } else {
//            return CommonResult.error("用户删除失败");
//        }
//    }
//
//    @PostMapping("/exchange")
//    public CommonResult<User> exchangeUser(@Valid @RequestBody ExchangeUserDTO exchangeUserDTO) {
//        User user = UserConverter.INSTANCE.exchangeUserDtoToUser(exchangeUserDTO);
//        boolean updated = userService.updateById(user);
//        if (updated) {
//            return CommonResult.success(user, "用户更新成功");
//        } else {
//            return CommonResult.error("用户更新失败");
//        }
//    }

    @Operation(summary = "创建新用户", description = "接收 AddUserDTO，转换为 User 并保存到数据库")
    @PostMapping("/user")
    @Cacheable(value = "user", key = "#addUserDTO.id")
    public CommonResult<User> addUser(@Valid @RequestBody AddUserDTO addUserDTO) {
        User user = UserConverter.INSTANCE.addUserDtoToUser(addUserDTO);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user, "用户创建成功");
        } else {
            return CommonResult.error("用户创建失败");
        }
    }

    @Operation(summary = "查找用户", description = "接收 FindUserDTO，查找用户")
    @GetMapping("/user")
    @Cacheable(value = "user", key = "#findUserDTO.id")
    public CommonResult<User> findUser(@Valid FindUserDTO findUserDTO) {
        User user = userService.findById(findUserDTO.getId());
        if (user != null) {
            return CommonResult.success(user, "用户查找成功");
        } else {
            return CommonResult.error("用户查找失败");
        }
    }

    @Operation(summary = "删除用户", description = "接收 DeleteUserDTO，删除用户")
    @DeleteMapping("/user")
    @Cacheable(value = "user", key = "#deleteUserDTO.id")
    public CommonResult<User> deleteUser(@Valid DeleteUserDTO deleteUserDTO) {
        boolean deleted = userService.deleteById(deleteUserDTO.getId());
        if (deleted) {
            return CommonResult.success(null, "用户删除成功");
        } else {
            return CommonResult.error("用户删除失败");
        }
    }

    @Operation(summary = "更新用户", description = "接收 ExchangeUserDTO，更新用户")
    @PutMapping("/user")
    @Cacheable(value = "user", key = "#exchangeUserDTO.id")
    public CommonResult<User> exchangeUser(@Valid @RequestBody ExchangeUserDTO exchangeUserDTO) {
        User user = UserConverter.INSTANCE.exchangeUserDtoToUser(exchangeUserDTO);
        boolean updated = userService.updateById(user);
        if (updated) {
            return CommonResult.success(user, "用户更新成功");
        } else {
            return CommonResult.error("用户更新失败");
        }
    }
}