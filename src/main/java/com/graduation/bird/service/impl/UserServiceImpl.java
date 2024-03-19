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

    // 根据id查找用户
    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    // 根据uid查找用户
    @Override
    public User findByUID(String UID) {
        return userMapper.findByUID(UID);
    }

    //根据手机号查找用户
    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userMapper.findByPhoneNumber(phoneNumber);
    }

    // 添加用户
    @Override
    public Boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    // 根据UID删除用户
    @Override
    public Boolean deleteUserByUID(String UID) {
        return userMapper.deleteUserByUID(UID);
    }

    //根据手机号删除用户
    @Override
    public Boolean deleteUserByPhoneNumber(String phoneNumber) {
        return userMapper.deleteUserByPhoneNumber(phoneNumber);
    }

    // 根据UID更新用户
    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

}
