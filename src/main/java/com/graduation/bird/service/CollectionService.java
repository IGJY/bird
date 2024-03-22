package com.graduation.bird.service;

import com.graduation.bird.entity.Collection;

import java.util.List;

public interface CollectionService {

    //查询所有收藏信息
    List<Collection> getAllCollection();

    //根据UID查询所有收藏信息
    List<Collection> getAllCollectionByUID(String UID);

    //添加收藏信息
    Boolean addCollection(Collection collection);

    //删除收藏信息
    Boolean deleteCollection(String UID, Long birdId);

}
