package com.graduation.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("history_record")
public class HistoryRecord {

    //id
    @TableId(type = IdType.AUTO)
    private Integer id;

    //UID
    private String UID;

    //时间
    private Date actionTime;

    //动作
    private String action;

    //结果
    private String result;

    //对应的鸟类url
    private String birdUrl;
}
