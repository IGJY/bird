package com.graduation.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("birds")
public class Birds {

    //鸟类id
    @TableId(type = IdType.AUTO)
    private Long id;

    //创建时间
    private Date createdAt;

    //更新时间
    private Date updatedAt;

    //形态及特征
    private String morphologyAndFeatures;

    //名字
    private String name;

    //生活习性
    private String habits;

    //种属（包括纲目科属）
    private String species;

    //图片
    private String imageUrl;

    //叫声
    private String soundUrl;

    //基本介绍
    private String description;

    //生长与分布
    private String growthAndDistribution;


}
