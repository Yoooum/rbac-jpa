package com.prprv.jpa.service.impl;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.repo.PermissionRepository;
import com.prprv.jpa.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Yoooum
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    public final PermissionRepository permissionRepository;

    @Override
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission select(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("不存在权限ID：" + id));
    }

    @Override
    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
