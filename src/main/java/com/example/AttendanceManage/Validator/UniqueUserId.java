package com.example.AttendanceManage.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserIdValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserId
{
    String message() default "このユーザIDは既に存在します";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
