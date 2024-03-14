package com.graduation.bird.service.impl;

import com.graduation.bird.entity.User;
import com.graduation.bird.mapper.UserMapper;
import com.graduation.bird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }

}
