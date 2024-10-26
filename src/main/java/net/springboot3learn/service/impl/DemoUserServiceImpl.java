package net.springboot3learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.springboot3learn.entity.user.DemoUser;
import net.springboot3learn.service.DemoUserService;
import net.springboot3learn.mapper.DemoUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 瓜瓜乐
* @description 针对表【demo_user(演示用户信息表)】的数据库操作Service实现
* @createDate 2024-10-26 21:46:49
*/
@Service
public class DemoUserServiceImpl extends ServiceImpl<DemoUserMapper, DemoUser>
    implements DemoUserService{

}




