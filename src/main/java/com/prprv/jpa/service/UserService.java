package com.prprv.jpa.service;

import com.prprv.jpa.entity.User;
import org.springframework.data.domain.Page;

/**
 * @author Yoooum
 */
public interface UserService {
    User createUser(User user);
    User selectUser(Long id);
    User updateUser(User user);
    void deleteUser(Long id);
    Page<User> selectUserPage(Integer page, Integer size);
}
