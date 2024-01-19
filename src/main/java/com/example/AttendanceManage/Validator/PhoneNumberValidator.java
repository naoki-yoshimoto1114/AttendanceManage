package com.example.AttendanceManage.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String>
{
    @Override
    public void initialize(PhoneNumber constraintAnnotation)
    {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if(value == null || value.isEmpty()){
            return true;
        }
        return value.matches("^\\d{11}$");
    }
}
