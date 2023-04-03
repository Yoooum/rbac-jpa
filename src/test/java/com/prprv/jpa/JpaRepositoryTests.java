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

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class JpaRepositoryTests {
    @Resource
    private PermissionRepository permissionRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        Permission read = new Permission();
        read.setPermission("test:read");
        read.setDescription("测试读取权限");

        Permission write = new Permission();
        write.setPermission("test:write");
        write.setDescription("测试写入权限");

        Set<Permission> permission = Set.of(read, write);
        List<Permission> savedPermission = permissionRepository.saveAll(permission);
        System.out.println("添加权限 " + savedPermission);


        Role role = new Role();
        role.setRole("ROLE_TEST");
        role.setName("测试角色");
        role.setDescription("测试角色，用于测试");
        role.setPermission(new HashSet<>(savedPermission));
        Role savedRole = roleRepository.save(role);
        System.out.println("添加角色 " + savedRole);


        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setBirthday(LocalDate.now());
        user.setPhone(123456789L);
        user.setGender("男");
        user.setAvatar("test.jpg");
        user.setDescription("测试用户，用于测试");
        User userSaved = userRepository.save(user);
        System.out.println("添加用户 " + userSaved);

        userSaved.setRole(Set.of(savedRole));
        userRepository.findById(userSaved.getId()).ifPresent(present -> {
            present.setRole(userSaved.getRole());
            User savedUserRole = userRepository.save(present);
            System.out.println("更新用户角色 " + savedUserRole);
        });

        roleRepository.findById(savedRole.getId()).ifPresent(present -> {
            present.setName("测试角色改");
            Role savedRoleUser = roleRepository.save(present);
            System.out.println("更新角色用户 " + savedRoleUser);
        });


    }
}
