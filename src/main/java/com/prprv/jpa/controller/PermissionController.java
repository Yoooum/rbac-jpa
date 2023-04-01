package com.prprv.jpa.controller;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.entity.request.PermissionRequest;
import com.prprv.jpa.service.impl.PermissionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/permission")
@RequiredArgsConstructor
@CrossOrigin
public class PermissionController {
    private final PermissionServiceImpl permissionService;
    @PostMapping("/create")
    public void createPermission(@RequestBody PermissionRequest permission) {
        Permission p = new Permission();
        p.setPermission(permission.permission());
        p.setDescription(permission.description());
        permissionService.createPermission(p);
    }

    @Transactional
    @DeleteMapping(value = "/delete", params = {"permission"})
    public void deletePermission(@RequestParam String permission) {
        permissionService.deletePermission(permission);
    }
    @PutMapping("/update")
    public void updatePermission(@RequestBody Map<String,String> permission) {
        permission.keySet().stream().map(permission::get).forEach(System.out::println);
    }

    @GetMapping("/query/all")
    public List<Permission> findAll() {
        return permissionService.queryPermissionAll();
    }

    @GetMapping(value = "/query", params = {"page", "size"})
    public void listPermission(@RequestParam int page, @RequestParam int size) {
        System.out.println(page + " " + size);
    }
}
