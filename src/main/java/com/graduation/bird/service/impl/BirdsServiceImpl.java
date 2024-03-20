package com.graduation.bird.service.impl;

import com.graduation.bird.entity.Birds;
import com.graduation.bird.mapper.BirdsMapper;
import com.graduation.bird.service.BirdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirdsServiceImpl implements BirdsService {

    @Autowired
    private BirdsMapper birdsMapper;

    //获取所有鸟类信息
    public List<Birds> getAllBirds()
    {
        return birdsMapper.getAllBirds();
    }

}
