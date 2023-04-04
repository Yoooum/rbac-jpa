package com.prprv.jpa.controller;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v2/permission")
public class PermissionControllerV2 extends BaseCrudController<User, UserRepository> {
    public PermissionControllerV2(UserRepository repository) {
        super(repository);
    }
}
