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

    @Override
    //获取所有鸟类信息
    public List<Birds> getAllBirds()
    {
        return birdsMapper.getAllBirds();
    }

    @Override
    //根据id获取 birds信息
    public Birds getBirdsById(Long id)
    {
        return birdsMapper.findById(id);
    }

    @Override
    //添加 birds信息
    public Boolean addBirds(Birds birds)
    {
        return birdsMapper.addBirds(birds);
    }

    @Override
    //删除 birds信息
    public Boolean deleteBirds(Long id)
    {
        return birdsMapper.deleteBirds(id);
    }

    @Override
    //更新 birds信息
    public Boolean updateBirds(Birds birds)
    {
        return birdsMapper.updateBirds(birds);
    }

}
