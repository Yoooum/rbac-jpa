package com.prprv.jpa.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import com.prprv.jpa.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yoooum
 */
@SpringBootTest
class UserServiceImplTest {

    @Resource
    UserService userService;

    @Resource
    UserRepository userRepository;

    @Resource
    JsonMapper jsonMapper;
    User userSaved;

    @Test
    void contextLoads() {
        createUser();
        selectUser();
        updateUser();
        deleteUser();
        selectUserPage();
    }

    void createUser() {
        User user = new User();
        user.setUsername("服务类测试");
        user.setPassword("123456");
        user.setEmail("service@prprv.com");
        userSaved = userService.create(user);
        System.out.println("create " + userSaved);
    }

    void selectUser() {
        User user = userService.select(userSaved.getId());
        System.out.println("select " + user);
    }

    void updateUser() {
        userSaved.setUsername("服务类测试更新");
        userSaved.setPhone(123456789L);
        userSaved.setBirthday(LocalDate.now());
        User user = userService.update(userSaved);
        System.out.println("update " + user);
    }

    void deleteUser() {
        userService.delete(userSaved.getId());
        System.out.println("delete " + userSaved);
    }

    void selectUserPage() {
        List<User> users = new ArrayList<>();
        // 生成30个用户，当前时间作为用户名密码邮箱
        for (int i = 0; i < 9; i++) {
            // 延迟
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User();
            user.setUsername(String.valueOf(System.currentTimeMillis()));
            user.setPassword(String.valueOf(System.currentTimeMillis()));
            user.setEmail(String.valueOf(System.currentTimeMillis()));
            users.add(user);
        }
        userRepository.saveAll(users);
        Page<User> page = userService.selectUserPage(0, 3);
        System.out.println("page " + jsonMapper.valueToTree(page));
    }
}
