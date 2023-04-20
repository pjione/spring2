package com.spring.repository;

import com.spring.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRepository {
    int insertUser(User user);
    User selectUser(@Param("id") String id);
    List<User> selectUserAll();
}
