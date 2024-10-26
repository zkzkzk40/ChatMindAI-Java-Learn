package net.chatmindai.springboot3learn.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.chatmindai.springboot3learn.entity.CommonResult;
import net.chatmindai.springboot3learn.entity.demo.dto.DemoDTO;
import net.chatmindai.springboot3learn.entity.user.User;
import net.chatmindai.springboot3learn.entity.user.UserConverter;
import net.chatmindai.springboot3learn.entity.user.dtos.UserLoginDto;
import net.chatmindai.springboot3learn.entity.user.dtos.UserQueryDto;
import net.chatmindai.springboot3learn.service.UserService;
import net.chatmindai.springboot3learn.utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println("Received user: " + user); // 输出接收到的用户数据
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
        boolean updated = userService.updateById(user);
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

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "接收 UserLoginDto，验证用户信息并返回 token")
    public CommonResult<User> login(@RequestBody UserLoginDto dto, HttpServletResponse response){
        System.out.println("Received user: " + dto); // 输出接收到的用户数据
        User user = userService.login(dto);
        if (user == null) {
            return CommonResult.error("用户不存在");
        }
        try {
            System.out.println("User from DB: " + user); // 输出从数据库查询到的用户数据
            Map<String, String> payload = new HashMap<>();
            payload.put("id",user.getId().toString());
            payload.put("name",user.getName());
            // 生成jwt令牌
            String token = JWTUtils.getToken(payload);
            response.setHeader("Authorization", "Bearer " + token);
            return CommonResult.success(user, "认证成功"+token);

        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }

    }

    @GetMapping("/login2")
    @Operation(summary = "用户登录", description = "接收 UserLoginDto，验证用户信息并返回 token")
    public Map<String,Object> login2(){
        Map<String,Object> map = new HashMap<>();
        try {
            User userDB = new User();
            userDB.setId(1L);
            userDB.setName("admin");
            System.out.println("User from DB: " + userDB); // 输出从数据库查询到的用户数据
            Map<String, String> payload = new HashMap<>();
            payload.put("id",userDB.getId().toString());
            payload.put("name",userDB.getName());
            // 生成jwt令牌
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功！");
            map.put("token",token);  // 响应token
        } catch (Exception e) {
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @PostMapping("/test")
    @Operation(summary = "测试jwt接口", description = "接收 token，验证 token 合法性")
    public Map<String,Object> test(@RequestBody String token){
        System.out.println("token = " + token);
        Map<String,Object> map = new HashMap<>();
        try {
            // 验证令牌
            DecodedJWT verify = JWTUtils.verify(token);
            map.put("state",true);
            map.put("msg","请求成功");
            return map;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","无效签名！");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","算法不一致");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效！");
        }
        map.put("state",false);
        return map;
    }

}