package com.spring.service;

import com.spring.domain.User;

public interface UserService {
    boolean joinUser(User user);
    User userCheck(String id);
}
