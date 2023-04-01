package com.prprv.jpa.service.impl;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.PermissionRepository;
import com.prprv.jpa.repo.RoleRepository;
import com.prprv.jpa.repo.UserRepository;
import com.prprv.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author Yoooum
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PermissionRepository permissionRepository;

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Set<User> findByRole(String role) {
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
