package com.prprv.jpa.controller;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.repo.PermissionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v2/permission")
public class PermissionControllerV2 extends AbstractCrudController<Permission, PermissionRepository> {
    public PermissionControllerV2(PermissionRepository repository) {
        super(repository);
    }
}
