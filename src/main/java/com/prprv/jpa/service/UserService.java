package com.prprv.jpa.service;

import com.prprv.jpa.entity.Role;
import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.RoleRepository;
import com.prprv.jpa.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yoooum
 */
@Service
public class UserService extends AbstractCrud<User, UserRepository> {
    protected UserService(UserRepository repository, RoleRepository roleRepository) {
        super(repository);
        this.roleRepository = roleRepository;
    }
    protected final RoleRepository roleRepository;
    public Page<User> findAll(Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return super.repository.findAll(pageable);
    }

    public User setRole(Long userId, Set<Long> roleId) {
        User user = super.repository.findById(userId).orElse(null);
        if (user != null) {
            List<Role> allById = roleRepository.findAllById(roleId);
            user.setRole(new HashSet<>(allById));
            return super.repository.saveAndFlush(user);
        }
        return null;
    }
}
