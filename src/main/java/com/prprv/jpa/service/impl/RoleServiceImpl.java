package com.prprv.jpa.service.impl;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.entity.Role;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.entity.request.RoleRequest;
import com.prprv.jpa.repo.PermissionRepository;
import com.prprv.jpa.repo.RoleRepository;
import com.prprv.jpa.repo.UserRepository;
import com.prprv.jpa.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yoooum
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;

    @Override
    public Role createRole(RoleRequest roleRequest) {
        // 查询并匹配数据库中的权限
        Set<Permission> byPermissionIn = permissionRepository.findByPermissionIn(roleRequest.permission());
        log.info("根据入参查询数据库中的权限: {}", byPermissionIn);
        roleRequest.permission().forEach(permission -> {
            if (!byPermissionIn.stream().map(Permission::getPermission).collect(Collectors.toSet()).contains(permission)) {
                log.error("数据库不存在入参权限: {}", permission);
                throw new RuntimeException("数据库不存在入参权限: " + permission);
            }
        });
        Role role = new Role();
        role.setRole(roleRequest.role());
        role.setName(roleRequest.name());
        role.setDescription(roleRequest.description());
        role.setPermission(byPermissionIn);
        return roleRepository.save(role);
    }

    // 添加用户
    public Role addUser(Set<Long> userId, Long roleId) {
        List<User> allById = userRepository.findAllById(userId);
        if (allById.size() != userId.size()) {
            log.error("用户不存在");
            throw new RuntimeException("用户不存在");
        }
        Role byRole = roleRepository.findById(roleId).orElseThrow(() -> {
            log.error("角色不存在");
            return new RuntimeException("角色不存在");
        });
        byRole.setUser(new HashSet<>(allById));
        return roleRepository.save(byRole);
    }

    public List<Role> listRole() {
        return roleRepository.findAll();
    }
}
