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
        return null;
    }

    @Override
    public Permission select(Long id) {
        return null;
    }

    @Override
    public Permission update(Permission permission) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
