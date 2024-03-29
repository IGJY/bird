package com.graduation.bird.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T>{
    private Long total;//总记录数
//    private T rows;//每页记录数
    private List<T> items; //当前页数据集合
}
