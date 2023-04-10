package com.prprv.jpa.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 这是一个实现 AuthenticationProvider 接口的类，用于验证 JWT。
 * 当用户提供有效的 JWT 时，JwtAuthenticationFilter 会调用 JwtAuthenticationProvider 进行验证。
 * 如果 JWT 有效，则 JwtAuthenticationProvider 会返回一个 Authentication 对象，其中包含用户信息，如用户名和角色。
 * @author Yoooum
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtUserDetailsService jwtUserDetailsService;

    public JwtAuthenticationProvider(JwtTokenProvider jwtTokenProvider, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        if (!jwtTokenProvider.validateToken(token)) {
            throw new BadCredentialsException("Invalid token");
        }

        String username = jwtTokenProvider.extractUsername(token);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
