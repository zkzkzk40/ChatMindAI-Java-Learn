package net.chatmindai.springboot3learn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.chatmindai.springboot3learn.entity.demo.dto.DemoDto;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.entity.CommonResult;
import net.chatmindai.springboot3learn.entity.user.UserConverter;
import net.chatmindai.springboot3learn.service.UserService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name="用户管理",description="用户相关的 API")
public class UserController {

    private final UserService userService;

    @Operation(summary="创建新的用户",description="接收 DemoDto，转换为User 并保存到数据库")
    @PostMapping("/register")
    public CommonResult<User> createUser(@Valid @RequestBody DemoDto demoDto){
        User user = UserConverter.INSTANCE.userDtoToUser(demoDto);
        boolean saved = userService.save(user);
        if (saved) {
            return CommonResult.success(user, "用户创建成功");
        } else {
            return CommonResult.error("用户创建失败");
        }
    }

    @Operation(summary="根据输入的id删除用户")
    @DeleteMapping("/removeById")
    public CommonResult<String> RemoveById(@Valid @RequestBody String userId){
        boolean removed = userService.removeById(userId);
        if (removed) {
            return CommonResult.success("用户删除成功");
        } else {
            return CommonResult.error("用户删除失败");
        }
    }

    @Operation(summary="批量删除用户")
    @DeleteMapping("/removeBatch")
    public CommonResult<String> removeBatch(@RequestBody List<Long> batchUserIds) {
        boolean removed = userService.removeByIds(batchUserIds);

        for (Long id : batchUserIds) {
            if (userService.getById(id) != null) {
                return CommonResult.error("用户删除失败，ID： " + id + "没有删除.");
            }
        }
        return CommonResult.success("批量用户删除成功");
    }

    @Operation(summary="根据输入的id查找用户")
    @PostMapping("/getById")
    public CommonResult<User> getById(@Valid @RequestBody String userId){
        User user = userService.getById(userId);
        if (user != null) {
            return CommonResult.success(user, "查找成功");
        } else {
            return CommonResult.error("查找失败，您查找的用户不存在");
        }
    }

    @Operation(summary="根据输入的姓名查找用户",description="重名的使用列表展示")
    @PostMapping("/getByName")
    public CommonResult<List<User>> getByName(@Valid @RequestBody String userName){
        List<User> userList = userService.getByName(userName);
        if (userList != null) {
            return CommonResult.success(userList, "查找成功");
        } else {
            return CommonResult.error("查找失败，您查找的用户不存在");
        }
    }

    @Operation(summary = "修改用户的成绩")
    @PutMapping("/updateUser")
    public CommonResult<User> updateUser(@RequestParam String userId,@RequestParam int addScore){
        User user = userService.getById(userId);
        user.setScore(user.getScore()+addScore);
        boolean update = userService.updateById(user);
        if (update) {
            return CommonResult.success(user,"成绩修改成功");
        } else {
            return CommonResult.error("成绩修改失败");
        }
    }
}
