package com.graduation.bird.service.impl;

import com.graduation.bird.entity.Birds;
import com.graduation.bird.entity.Result;
import com.graduation.bird.mapper.BirdsMapper;
import com.graduation.bird.service.BirdsService;
import com.graduation.bird.utils.MergeUtil;
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
    public Result updateBirds(Birds birds)
    {
        //判断是否能够找到该鸟类
        Birds oldBirds = getBirdsById(birds.getId());
        if (oldBirds == null)
        {
            return Result.error("该 birds信息不存在");

        }else {

            //利用工具类进行合并
            birds = MergeUtil.mergeObjects(oldBirds,birds);

            return Result.success(birdsMapper.updateBirds(birds));
        }

    }

}
