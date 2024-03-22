package com.graduation.bird.service.impl;

import com.graduation.bird.entity.Collection;
import com.graduation.bird.mapper.CollectionMapper;
import com.graduation.bird.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;


    //获取所有收藏信息
    @Override
    public List<Collection> getAllCollection()
    {
        return collectionMapper.getAllCollection();
    }

    //根据UID获取所有收藏信息
    @Override
    public List<Collection> getAllCollectionByUID(String UID)
    {
        return collectionMapper.getAllCollectionByUID(UID);
    }

    //添加收藏信息
    @Override
    public Boolean addCollection(Collection collection)
    {

        //TODO 判断该鸟类和用户是否存在,需要吗？

        return collectionMapper.addCollection(collection);
    }

    //删除收藏信息
    @Override
    public Boolean deleteCollection(String UID, Long birdId)
    {
        return collectionMapper.deleteCollection(UID, birdId);
    }

}
