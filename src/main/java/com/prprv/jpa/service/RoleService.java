package com.prprv.jpa.service;

import com.prprv.jpa.entity.Role;
import com.prprv.jpa.entity.request.RoleRequest;

import java.util.Set;

/**
 * @author Yoooum
 */
public interface RoleService {

    Role createRole(RoleRequest roleRequest);
}
