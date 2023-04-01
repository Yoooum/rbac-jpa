package com.prprv.jpa;

import com.prprv.jpa.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJpaApplicationTests {

    @Resource
    private UserService userService;
    @Test
    void contextLoads() {

    }

    @Test
    void test() {



    }

}
