package com.prprv.jpa.service;

import com.prprv.jpa.entity.User;

import java.util.Set;

/**
 * @author Yoooum
 */
public interface UserService {
    void createUser(User user);
    Set<User> findByRole(String role);
}
