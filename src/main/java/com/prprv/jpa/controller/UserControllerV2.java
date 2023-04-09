package com.prprv.jpa.controller;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import com.prprv.jpa.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v2/user")
public class UserControllerV2 extends AbstractCrudController<User, UserRepository> {
    public UserControllerV2(UserRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    private final UserService userService;

    @PutMapping(value = "/role",params = {"userId"})
    public User setRole(Long userId, @RequestBody Set<Long> roleId) {
        return userService.setRole(userId, roleId);
    }
}
