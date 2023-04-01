package com.prprv.jpa.service;

import com.prprv.jpa.entity.Permission;

import java.util.Set;

/**
 * @author Yoooum
 */
public interface PermissionService {
    Permission createPermission(Permission permission);

    Iterable<Permission> createPermission(Set<String> permission);
}
