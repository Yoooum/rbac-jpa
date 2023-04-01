package com.prprv.jpa;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.entity.Role;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.PermissionRepository;
import com.prprv.jpa.repo.RoleRepository;
import com.prprv.jpa.repo.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;
import java.util.Set;

@SpringBootTest
class JpaRepositoryTests {
    @Resource
    UserRepository userRepository;
    @Resource
    RoleRepository roleRepository;
    @Resource
    PermissionRepository permissionRepository;
    @Test
    void contextLoads() {
        Permission permission = new Permission();
        permission.permission("read");
        permissionRepository.save(permission);

        Role role = new Role();
        role.role("ROLE_USER");
        role.permission(Set.of(permission));
        roleRepository.save(role);


        User user = new User();
        user.username("test");
        user.password("test");
        user.role(Set.of(role));
        userRepository.save(user);
    }

    @Test
    void test() {
        User user = new User();
        user.username("t");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatcher::startsWith)
                .withIgnorePaths("password");
        Example<User> example = Example.of(user, matcher);
        List<User> all = userRepository.findAll(example);
        System.out.println(all);
    }

}
