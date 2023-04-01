package com.prprv.jpa.service;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yoooum
 */
@SpringBootTest
class UserServiceImplTest {
    @Resource
    private UserService userService;
    @Resource
    UserRepository userRepository;
    @Test
    void createUser() {
        User user = new User();
        user.username("测试用户");
        user.password("测试用户");
        userService.createUser(user);
    }

    @Test
    void testFind() {
        userRepository.findByUsername("测试用户").ifPresent(System.out::println);
    }
}
