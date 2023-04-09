package com.prprv.jpa.controller;

import com.prprv.jpa.entity.Role;
import com.prprv.jpa.repo.RoleRepository;
import com.prprv.jpa.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v2/role")
public class RoleControllerV2 extends AbstractCrudController<Role, RoleRepository> {
    public RoleControllerV2(RoleRepository repository, RoleService roleService) {
        super(repository);
        this.roleService = roleService;
    }
    private final RoleService roleService;

    @Operation(summary = "setPermission", description = "给角色设置权限")
    @PutMapping(value = "/permission",params = {"roleId"})
    public Role setPermission(Long roleId, @RequestBody Set<Long> permissionId) {
        return roleService.setPermission(roleId, permissionId);
    }
}
