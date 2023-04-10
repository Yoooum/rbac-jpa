package com.prprv.jpa.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 这是一个实现 AuthenticationEntryPoint 接口的类，用于处理未经授权的请求。
 * 当用户试图访问需要授权的资源时，如果用户没有提供有效的 JWT，则 JwtAuthenticationFilter 会调用 JwtAuthenticationEntryPoint，
 * 返回 HTTP 401 Unauthorized 响应。
 * @author Yoooum
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
