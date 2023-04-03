package com.prprv.jpa.repo;

import com.prprv.jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yoooum
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
