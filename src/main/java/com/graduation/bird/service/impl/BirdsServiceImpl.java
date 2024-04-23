package com.graduation.bird.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.graduation.bird.entity.Birds;
import com.graduation.bird.entity.PageBean;
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
        //输出看一下
//        System.out.println(birds);

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

    @Override
    public Result<PageBean<Birds>> getBirdsByPage(int pageNum, int pageSize, String name) {
        //创建pagebean对象
        PageBean<Birds> pageBean = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //调用mapper
        List<Birds> birdsList = birdsMapper.getBirdsByPage(pageSize, (pageNum - 1) * pageSize,name);
        Page<Birds> page = (Page<Birds>) birdsList;

        //把数据填充到pageBean
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());

        return  Result.success(pageBean);

    }

}
