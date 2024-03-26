package com.graduation.bird.anno;

import com.graduation.bird.validation.UserTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented//元注解
@Target({FIELD})//元注解,用来指定注解的作用范围
@Retention(RUNTIME)//元注解,用来指定注解的生命周期
@Constraint(validatedBy = {UserTypeValidator.class})//指定提供校验规则的类
public @interface UserType {

    //校验失败后的提示信息
    String message() default "userType参数不合法，只能是admin|user";

    //指定分组
    Class<?>[] groups() default {};

    //负载, 获取到注解的附加信息
    Class<? extends Payload>[] payload() default {};

}
