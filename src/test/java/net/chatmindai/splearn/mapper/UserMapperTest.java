package net.chatmindai.splearn.mapper;

import static org.junit.jupiter.api.Assertions.*;
import net.chatmindai.splearn.entity.user.User;
import net.chatmindai.splearn.entity.user.dtos.UserQueryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        log.info("setUp");
    }

    @Test
    void testSelectUserById() {
        // 首先插入一个用户
        User user = createUser("Test User", 30, "test@example.com", Arrays.asList("reading", "swimming"));
        userMapper.insert(user);
        // 确保用户ID不为空
        assertNotNull(user.getId());

        // 使用selectUserById方法查询用户
        UserQueryDto dto = new UserQueryDto();
        dto.setId(user.getId());
        User foundUser = userMapper.selectUserById(dto);

        // 验证查询结果
        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
        assertEquals("Test User", foundUser.getName());
        assertEquals(30, foundUser.getAge());
        assertEquals("test@example.com", foundUser.getEmail());
    }

    private User createUser(String name, int age, String email, List<String> hobbies) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setHobbies(hobbies);
        user.setCreateAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
        user.setAgreeTerms(1);
        user.setScore(85.5);
        user.setPhoneNumber("13800138000");
        return user;
    }
}