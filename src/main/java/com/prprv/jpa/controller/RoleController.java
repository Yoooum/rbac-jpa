package com.prprv.jpa.controller;

import com.prprv.jpa.entity.Role;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.entity.request.RoleRequest;
import com.prprv.jpa.service.UserService;
import com.prprv.jpa.service.impl.RoleServiceImpl;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/role")
@CrossOrigin
@RequiredArgsConstructor
public class RoleController {
    private final RoleServiceImpl roleService;
    private final UserService userService;
    @PostMapping("/create")
    public void createRole(@RequestBody RoleRequest role) {
        roleService.createRole(role);
    }

    @DeleteMapping("/delete/{role}")
    public void deleteRole(@NotBlank @PathVariable String role) {
        System.out.println(role.toUpperCase());
    }

    @PutMapping("/update")
    public void updateRole(@RequestBody RoleRequest roleRequest) {
        System.out.println(roleRequest);
    }

    @GetMapping("/query/{role}")
    public void findRole(@NotBlank @PathVariable String role) {
        System.out.println(role.toUpperCase());
    }

    @GetMapping(value = "/query/user", params = {"role"})
    public Set<User> findUserByRole(@RequestParam String role) {
        return userService.findByRole(role.toUpperCase());
    }
    @GetMapping(value = "/query/all")
    public List<Role> findAll() {
        return roleService.listRole();
    }

    // 修改拥有的用户
    @PutMapping(value = "/update/user")
    public void updateUser(@RequestBody Long roleId, @RequestBody Long[] userId) {
        HashSet<Long> userIdSet = new HashSet<>(List.of(userId));
        System.out.println(roleId + " " + userIdSet);
        //roleService.addUser(userIdSet, roleId);
    }

    @GetMapping(value = "/query", params = {"page", "size"})
    public void listRole(@RequestParam int page, @RequestParam int size) {
        System.out.println(page + " " + size);
    }

}
