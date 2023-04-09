package com.prprv.jpa.service;

import com.prprv.jpa.entity.Permission;
import com.prprv.jpa.entity.Role;
import com.prprv.jpa.repo.PermissionRepository;
import com.prprv.jpa.repo.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yoooum
 */
@Service
public class RoleService extends AbstractCrud<Role, RoleRepository>{
    protected RoleService(RoleRepository repository, PermissionRepository permissionRepository) {
        super(repository);
        this.permissionRepository = permissionRepository;
    }
    private final PermissionRepository permissionRepository;
    public Role setPermission(Long roleId, Set<Long> permissionId) {
        Role role = super.repository.findById(roleId).orElse(null);
        if (role != null) {
            List<Permission> allById = permissionRepository.findAllById(permissionId);
            role.setPermission(new HashSet<>(allById));
            return super.repository.saveAndFlush(role);
        }
        return null;
    }
}
