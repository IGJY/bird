package com.graduation.bird.controller;

import com.graduation.bird.entity.User;
import com.graduation.bird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findById")
    public User findById(String id) {
        return userService.findById(id);
    }
//    public String findById(String id) {
//        return "123";
//    }

}
