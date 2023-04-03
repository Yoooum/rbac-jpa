package com.prprv.jpa.service.impl;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.repo.PermissionRepository;
import com.prprv.jpa.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Yoooum
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    public final PermissionRepository permissionRepository;

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Iterable<Permission> createPermission(Set<String> permission) {
        return null;
    }

    public List<Permission> queryPermissionAll() {
        return permissionRepository.findAll();
    }

    public void deletePermission(String permission) {
        permissionRepository.deletePermissionByPermission(permission);
    }
}
