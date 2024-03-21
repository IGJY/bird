package com.graduation.bird.controller;

import com.graduation.bird.entity.Birds;
import com.graduation.bird.entity.Result;
import com.graduation.bird.service.BirdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/birds")
public class BirdsController {

    @Autowired
    private BirdsService birdsService;

    //获取所有鸟类信息
    //TODO 测试
    @PostMapping("/getAllBirds")
    public Result<List<Birds>> getAllBirds()
    {
        return Result.success(birdsService.getAllBirds());
    }

}
