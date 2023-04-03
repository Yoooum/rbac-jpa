package com.prprv.jpa.service;

import com.prprv.jpa.entity.User;
import org.springframework.data.domain.Page;

/**
 * @author Yoooum
 */
public interface UserService extends Crud<User> {
    Page<User> selectUserPage(Integer page, Integer size);
}
