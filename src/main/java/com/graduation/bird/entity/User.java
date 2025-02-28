package com.graduation.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduation.bird.anno.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

//    TODO 加上这个的话会出现注册的时候没有传入userType导致的失败
//    @UserType
    private String userType;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,18}$", message = "密码必须为6-18位的字母或数字")
    @JsonIgnore
    private String password;

    private String nickname;

    private String introduction;

    //TODO 枚举？
    private String gender;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号必须为11位数字")
    private String phoneNumber;


}
