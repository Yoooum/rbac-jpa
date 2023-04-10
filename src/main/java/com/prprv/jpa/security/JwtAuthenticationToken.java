package com.prprv.jpa.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Yoooum
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;
    private final UserDetails userDetails;

    public JwtAuthenticationToken(String token, UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }
}
