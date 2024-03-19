package com.graduation.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collection")
public class Collection {

    //id
    @TableId(type = IdType.AUTO)
    private int id;

    //用户id
    private int UID;

    //鸟id
    private int birdId;

    //鸟类url
    private String birdUrl;
}
