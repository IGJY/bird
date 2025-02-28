package com.graduation.bird.service;

import com.graduation.bird.entity.Birds;
import com.graduation.bird.entity.PageBean;
import com.graduation.bird.entity.Result;

import java.util.List;

public interface BirdsService {

    //获取所有鸟类信息
    List<Birds> getAllBirds();

    //根据id获取鸟类信息
    Birds getBirdsById(Long id);

    //添加鸟类
    Boolean addBirds(Birds birds);

    //删除鸟类
    Boolean deleteBirds(Long id);

    //更新鸟类
    Result updateBirds(Birds birds);

    //分页查询鸟类信息
    Result<PageBean<Birds>> getBirdsByPage(int pageNum, int pageSize, String name);

    Birds getBirdsByName(String name);
}
