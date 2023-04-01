package com.prprv.jpa.entity.request;

import com.prprv.jpa.entity.User;

import java.util.Set;

/**
 * @author Yoooum
 */
public record UserRequest(User user, Set<String> role, Options options) {
    public record Options(Boolean random, Boolean modify, Boolean send) {
    }
}
