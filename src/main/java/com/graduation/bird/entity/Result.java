package com.graduation.bird.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {

    //状态码 200成功 500失败
    private int code;
    //提示信息
    private String msg;
    //返回数据
    private T data;

    //带参数的成功返回
    public static <E> Result<E> success(E data) {
        return new Result<E>(200, "success", data);
    }

    //不带参数的成功返回
    public  static  Result success() {
        return new Result(200, "success", null);
    }

    //带参数的失败返回
    public static <E> Result<E> error(String msg, E data) {
        return new Result<E>(500, msg, data);
    }

    //不带参数的失败返回
    public static Result error(String msg) {
        return new Result(500, msg, null);
    }

}
