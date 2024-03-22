package com.graduation.bird.controller;

import com.graduation.bird.entity.Collection;
import com.graduation.bird.entity.Result;
import com.graduation.bird.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    //获取所有收藏信息
    //TODO 测试
    @PostMapping("/getAllCollection")
    public Result<List<Collection>> getAllCollection() {
        return Result.success(collectionService.getAllCollection());
    }

    //根据UID获取所有收藏信息
    //TODO 测试
    @PostMapping("/getAllCollectionByUID")
    public Result<List<Collection>> getAllCollectionByUID(String UID) {
        return Result.success(collectionService.getAllCollectionByUID(UID));
    }

    //添加收藏信息
    //TODO 测试
    @PostMapping("/addCollection")
    public Result addCollection(Collection collection) {
        return Result.success(collectionService.addCollection(collection));
    }

    //删除收藏信息
    //TODO 测试
    @PostMapping("/deleteCollection")
    public Result deleteCollection(String UID, Long birdId) {
        return Result.success(collectionService.deleteCollection(UID, birdId));
    }


}
