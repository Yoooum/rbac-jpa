package com.prprv.jpa.service;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.repo.PermissionRepository;
import org.springframework.stereotype.Service;

/**
 * @author Yoooum
 */
@Service
public class PermissionService extends AbstractCrud<Permission, PermissionRepository> {
    protected PermissionService(PermissionRepository repository) {
        super(repository);
    }
}
