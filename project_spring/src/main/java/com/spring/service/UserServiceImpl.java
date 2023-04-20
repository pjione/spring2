package com.spring.service;

import com.spring.domain.User;
import com.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public boolean joinUser(User user) {
        return userRepository.insertUser(user) == 1 ? true : false;
    }
    @Override
    public User userCheck(String id) {
        return userRepository.selectUser(id);
    }
}
