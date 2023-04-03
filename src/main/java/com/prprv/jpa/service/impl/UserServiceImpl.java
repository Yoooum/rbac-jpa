package com.prprv.jpa.service.impl;

import com.prprv.jpa.entity.User;
import com.prprv.jpa.repo.UserRepository;
import com.prprv.jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Yoooum
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User select(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("不存在的用户ID " + id));
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) throw new RuntimeException("用户ID不能为空，更新失败");
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> selectUserPage(Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return userRepository.findAll(pageable);
    }
}
