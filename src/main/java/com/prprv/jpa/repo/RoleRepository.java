package com.prprv.jpa.repo;

import com.prprv.jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Yoooum
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

    Set<Role> findByRoleIn(Set<String> role);
}
