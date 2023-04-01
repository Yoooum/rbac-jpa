package com.prprv.jpa.service;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

/**
 * @author Yoooum
 */
@SpringBootTest
class RoleServiceImplTest {

    @Resource
    private RoleService roleService;

//    @Test
//    void createRole() {
//        try {
//            roleService.createRole("ROLE_USER", Set.of("read"));
//        } catch (ConstraintViolationException e) {
//            System.out.println(e.getClass().getName());
//            System.out.println(e.getConstraintViolations());
//        }
//    }

    @Test
    void testCreateRole() {
    }
}
