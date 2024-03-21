package com.graduation.bird.service;

import com.graduation.bird.entity.Birds;

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
    Boolean updateBirds(Birds birds);

}
