package com.graduation.bird.controller;

import com.graduation.bird.entity.Result;
import com.graduation.bird.entity.User;
import com.graduation.bird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //根据id查找用户
    @PostMapping("/findById")
    public  Result<User> findById(Long id) {
        return Result.success(userService.findById(id));
    }
//    public String findById(String id) {
//        return "123";
//    }

    //根据uid查找用户
    @PostMapping("/findByUID")
    public Result<User> findByUID(String UID) {
        return Result.success(userService.findByUID(UID));
    }

    //根据手机号查找用户
    @PostMapping("/findByPhoneNumber")
    public Result<User> findByPhoneNumber(String phoneNumber) {
        return Result.success(userService.findByPhoneNumber(phoneNumber));
    }

    //添加用户(注册)
    @PostMapping("/addUser")
    public Result addUser(User user) {
        //根据手机号判断用户是否已存在，如果存在则返回用户已存在
        if (userService.findByPhoneNumber(user.getPhoneNumber()) != null) {
            return Result.error("用户已存在");
        } else {
            return Result.success(userService.addUser(user));
        }
    }

    //根据UID删除用户
    @PostMapping("/deleteUserByUID")
    public Result deleteUserByUID(String UID) {
        return Result.success(userService.deleteUserByUID(UID));
    }

    //根据手机号删除用户
    @PostMapping("/deleteUserByPhoneNumber")
    public Result deleteUserByPhoneNumber(String phoneNumber) {
        return Result.success(userService.deleteUserByPhoneNumber(phoneNumber));
    }

    //根据UID更新用户
    @PostMapping("/updateUser")
    public Result updateUser(User user) {
        return Result.success(userService.updateUser(user));
    }

}
