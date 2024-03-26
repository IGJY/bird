package com.graduation.bird.validation;

import com.graduation.bird.anno.UserType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<UserType, String> {

    /**
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return 如果返回false 则表示校验不通过， 如果返回true 则表示校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //提供校验规则
        if (value == null) {
            return false;
        }
        if (value.equals("admin") || value.equals("user")) {
            return true;
        }
        return false;
    }

}
