package com.example.AttendanceManage.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "正しい電話番号を入力してください。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
