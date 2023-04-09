package com.prprv.jpa.repo;

import com.prprv.jpa.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Yoooum
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    void deletePermissionByPermission(String permission);

    Set<Permission> findByPermissionIn(Set<String> permission);
}
