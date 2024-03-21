package com.graduation.bird.service.info;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

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
