package com.prprv.jpa.entity.request;

import java.util.Set;

/**
 * @author Yoooum
 */
public record RoleRequest(String role,String name, String description, Set<String> permission) {
}
