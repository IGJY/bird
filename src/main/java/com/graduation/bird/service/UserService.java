package com.graduation.bird.service;

import com.graduation.bird.entity.User;

public interface UserService {

    // 根据id查找用户
    User findById(Long id);

    // 根据UID查找用户
    User findByUID(String UID);

    // 根据手机号查找用户
    User findByPhoneNumber(String phoneNumber);

    // 添加用户
    Boolean  addUser(User user);

    // 根据UID删除用户
    Boolean deleteUserByUID(String UID);

    //根据手机号删除用户
    Boolean deleteUserByPhoneNumber(String phoneNumber);

    // 根据UID更新用户
    Boolean updateUser(User user);

}
