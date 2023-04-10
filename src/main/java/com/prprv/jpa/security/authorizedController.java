package com.prprv.jpa.security;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yoooum
 */
@RestController
public class authorizedController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public authorizedController(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    record LoginRequest(String username, String password) {
    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.username;
        String password = loginRequest.password;
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());
        List<String> authorities = new ArrayList<>();
        user.getRole().forEach(role -> {
            authorities.add(role.getRole());
            role.getPermission().forEach(permission -> {
                authorities.add(permission.getPermission());
            });
        });
        claims.put("authorities", authorities);
        String token = jwtTokenProvider.generateToken(user.getUsername(),authorities);
        claims.put("token", token);
        return claims;

    }
}
