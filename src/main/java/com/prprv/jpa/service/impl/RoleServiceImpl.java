package com.prprv.jpa.service.impl;

import com.prprv.jpa.entity.Role;
import com.prprv.jpa.repo.RoleRepository;
import com.prprv.jpa.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Yoooum
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public Role select(Long id) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
