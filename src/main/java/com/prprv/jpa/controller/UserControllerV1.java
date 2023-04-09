package com.prprv.jpa.controller;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yoooum
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControllerV1 {
    private final UserService userService;
    @Parameters({
            @Parameter()
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User save( User user) {
        return userService.save(user);
    }

    @PutMapping("/update")
    public User update(User user) {
        return userService.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/list")
    public List<User> findAll() {
        return userService.findAll();
    }
}
