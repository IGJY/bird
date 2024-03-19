package com.graduation.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String UID;

    private Date createTime;

    private Date updateTime;

    private Integer userType;

    private String password;

    private String nickname;

    private String introduction;

    //TODO 枚举？
    private String gender;

    private String phoneNumber;


}
