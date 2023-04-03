package com.prprv.jpa.repo;

import com.prprv.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yoooum
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
