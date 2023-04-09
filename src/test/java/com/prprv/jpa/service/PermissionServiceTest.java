package com.prprv.jpa.service;

import com.prprv.jpa.entity.Permission;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yoooum
 */
@SpringBootTest
class PermissionServiceTest {
    @Resource
    PermissionService permissionService;

    @Test
    public void contextLoads() {
        Permission read = new Permission();
        read.setPermission("user:read");
        read.setDescription("用户读取权限");
        permissionService.save(read);

        Permission write = new Permission();
        write.setPermission("user:write");
        write.setDescription("用户写入权限");
        permissionService.save(write);
    }
}
