package com.graduation.bird.service.impl;

import com.graduation.bird.entity.Result;
import com.graduation.bird.entity.User;
import com.graduation.bird.mapper.UserMapper;
import com.graduation.bird.service.UserService;
import com.graduation.bird.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //获取所有用户信息
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

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
        // 对密码进行MD5加密
        user.setPassword(MD5Utils.encrypt(user.getPassword()));

        //使用UUID自动生成唯一UID
        user.setUID(UUID.randomUUID().toString().replace("-", ""));

        return userMapper.addUser(user);
    }

    //注册
    @Override
    public Result register(User user) {

        //根据手机号判断用户是否已存在，如果存在则返回用户已存在
        if (findByPhoneNumber(user.getPhoneNumber()) != null) {

            return Result.error("用户已存在");

        } else {

            // 对密码进行MD5加密
            user.setPassword(MD5Utils.encrypt(user.getPassword()));

            //使用UUID自动生成唯一UID
            user.setUID(UUID.randomUUID().toString().replace("-", ""));

            return Result.success(userMapper.addUser(user));

        }

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
