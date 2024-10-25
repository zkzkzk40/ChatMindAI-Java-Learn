package net.chatmindai.splearn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.chatmindai.splearn.entity.CommonResult;
import net.chatmindai.splearn.entity.demo.dto.DemoDTO;
import net.chatmindai.splearn.entity.user.User;
import net.chatmindai.splearn.entity.user.UserConverter;
import net.chatmindai.splearn.entity.user.dtos.UserQueryDto;
import net.chatmindai.splearn.mapper.UserMapper;
import net.chatmindai.splearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关的 API")
public class UserController {
    private final UserService userService;
    @Autowired
    private UserMapper userMapper;

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

    @GetMapping("/find")
    @Operation(summary = "查询用户信息",description = "接收id信息,返回对应id的用户信息")
    public CommonResult<User> findUser(@RequestParam(value = "id",required = false) String id) {
        UserQueryDto dto = new UserQueryDto();
        if (id == null || id.trim().isEmpty()) {
            return CommonResult.error("id为空");
        }
        Long userId;
        try {
            userId = Long.valueOf(id);
        } catch (Exception e) {
            return CommonResult.error("id格式错误");
        }
        dto.setId(userId);
        User foundUser = userMapper.selectUserById(dto);
        if (foundUser == null) {
            return CommonResult.error("未查找到该用户");
        }
        return CommonResult.success(foundUser, "成功找到该用户");
    }

    @PutMapping("/edit")
    @Operation(summary = "修改用户信息",description = "先查询用户是否存在,再接收修改信息,但不能违规")
    public CommonResult<User> updateUser(@RequestParam String id,@Valid @RequestBody DemoDTO demoDTO) {
        UserQueryDto dto = new UserQueryDto();
        if (id == null || id.trim().isEmpty()) {
            return CommonResult.error("id为空");
        }
        Long userId;
        try {
            userId = Long.valueOf(id);
        } catch (Exception e) {
            return CommonResult.error("id格式错误");
        }
        dto.setId(userId);
        User foundUser = userMapper.selectUserById(dto);
        if (foundUser == null) {
            return CommonResult.error("该用户不存在");
        }
        User dataUser = UserConverter.INSTANCE.userDtoToUser(demoDTO);
        LocalDateTime now = LocalDateTime.now();
        User user = userService.getById(userId);
        user.setName(dataUser.getName());
        user.setAge(dataUser.getAge());
        user.setEmail(dataUser.getEmail());
        user.setPhoneNumber(dataUser.getPhoneNumber());
        user.setBirthDate(dataUser.getBirthDate());
        user.setPlanDate(dataUser.getPlanDate());
        user.setScore(dataUser.getScore());
        user.setHobbies(dataUser.getHobbies());
        user.setUpdateAt(now);
        boolean update = userService.updateById(user);
        if (update) {
            return CommonResult.success(user, "用户更新成功");
        } else {
            return CommonResult.error("用户更新失败");
        }
    }
}
