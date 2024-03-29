package com.graduation.bird.controller;

import com.graduation.bird.entity.Birds;
import com.graduation.bird.entity.PageBean;
import com.graduation.bird.entity.Result;
import com.graduation.bird.service.BirdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/birds")
public class BirdsController {

    @Autowired
    private BirdsService birdsService;

    //获取所有鸟类信息
    @PostMapping("/getAllBirds")
    public Result<List<Birds>> getAllBirds()
    {
        return Result.success(birdsService.getAllBirds());
    }

    //根据id获取 birds信息
    @PostMapping("/getBirdsById")
    public Result<Birds> getBirdsById(Long id)
    {
        return Result.success(birdsService.getBirdsById(id));
    }

    //添加鸟类
    @PostMapping("/addBirds")
    public Result addBirds(@Validated Birds birds)
    {
        return Result.success(birdsService.addBirds(birds));
    }

    //删除鸟类
    @PostMapping("/deleteBirds")
    public Result deleteBirds(Long id)
    {
        return Result.success(birdsService.deleteBirds(id));
    }

    //更新 birds信息
    @PostMapping("/updateBirds")
    public Result updateBirds(@Validated(Birds.Update.class) Birds birds)
    {
        return birdsService.updateBirds(birds);
    }

    //分页查询鸟类信息
    @PostMapping ("/getBirdsByPage")
    public Result<PageBean<Birds>> getBirdsByPage(
            int pageNum,
            int pageSize,
            @RequestParam(required = false) String name
    )
    {
//        System.out.println(pageNum);
//        System.out.println(pageSize);
        return birdsService.getBirdsByPage(pageNum,pageSize,name);
    }

}
