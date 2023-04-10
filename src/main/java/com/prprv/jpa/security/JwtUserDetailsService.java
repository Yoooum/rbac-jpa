package com.prprv.jpa.security;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个实现 UserDetailsService 接口的类，用于从数据库或其他数据源中加载用户信息。
 * JwtAuthenticationProvider 会调用 JwtUserDetailsService 加载用户信息，以验证 JWT 中的用户信息是否有效。
 * @author Yoooum
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 从数据库中加载用户信息，然后将用户的角色和权限信息转换为Spring Security中的GrantedAuthority对象，并构造一个UserDetails对象来表示这个用户的身份。
     * @param username 用户名
     * @return UserDetails UserDetails实现类，这里使用Security自带的User
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
            role.getPermission().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            });
        });
        // 返回一个UserDetails实现类，这里使用Security自带的User
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
