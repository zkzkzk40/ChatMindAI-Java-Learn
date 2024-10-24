package net.chatmindai.splearn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import net.chatmindai.splearn.entity.user.User;
import net.chatmindai.splearn.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    private static Long userId;
    private static List<Long> batchUserIds;

    @Test
    @Order(1)
    public void testSave() {
        User user = new User();
        user.setName("测试用户");
        user.setEmail("test@example.com");
        user.setAge(25);
        user.setPhoneNumber("13800138000");
        user.setBirthDate(LocalDateTime.now().minusYears(25));
        user.setPlanDate(LocalDateTime.now().plusDays(30));
        user.setScore(85.5);
        user.setHobbies(Arrays.asList("读书", "运动"));
        user.setAgreeTerms(1);
        boolean save = userService.save(user);
        assertTrue(save);
        assertNotNull(user.getId());
        userId = user.getId();
    }

    @Test
    @Order(2)
    public void testGetById() {
        User foundUser = userService.getById(userId);
        assertNotNull(foundUser);
        assertEquals("测试用户", foundUser.getName());
    }

    @Test
    @Order(3)
    public void testUpdate() {
        User user = userService.getById(userId);
        user.setName("更新后的用户名");
        boolean update = userService.updateById(user);
        assertTrue(update);

        User updatedUser = userService.getById(userId);
        assertEquals("更新后的用户名", updatedUser.getName());
    }

    @Test
    @Order(4)
    public void testQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "更新后的用户名");
        User queriedUser = userService.getOne(queryWrapper);
        assertNotNull(queriedUser);
        assertEquals(25, queriedUser.getAge());
    }

    @Test
    @Order(5)
    public void testSaveBatch() {
        List<User> users = Arrays.asList(
                createUser("批量用户1", "batch1@example.com", 30),
                createUser("批量用户2", "batch2@example.com", 35)
        );
        boolean saveBatch = userService.saveBatch(users);
        assertTrue(saveBatch);
        batchUserIds = users.stream().map(User::getId).toList();
    }

    @Test
    @Order(6)
    public void testPage() {
        Page<User> page = new Page<>(1, 10);
        Page<User> userPage = userService.page(page);
        assertFalse(userPage.getRecords().isEmpty());
        assertTrue(userPage.getTotal() >= 3);
    }

    @Test
    @Order(7)
    public void testRemoveById() {
        boolean remove = userService.removeById(userId);
        assertTrue(remove);
        assertNull(userService.getById(userId));
    }

    @Test
    @Order(8)
    public void testRemoveBatch() {
        boolean removeBatch = userService.removeByIds(batchUserIds);
        assertTrue(removeBatch);
        for (Long id : batchUserIds) {
            assertNull(userService.getById(id));
        }
    }

    private User createUser(String name, String email, int age) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        user.setPhoneNumber("13800138000");
        user.setBirthDate(LocalDateTime.now().minusYears(age));
        user.setPlanDate(LocalDateTime.now().plusDays(30));
        user.setScore(85.5);
        user.setHobbies(Arrays.asList("读书", "运动"));
        user.setAgreeTerms(1);
        return user;
    }
}