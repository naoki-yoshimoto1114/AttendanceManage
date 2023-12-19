package com.example.AttendanceManage.Validator;

import com.example.AttendanceManage.repositories.UserCrudRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserIdValidator implements ConstraintValidator<UniqueUserId, String>
{
    @Autowired
    private UserCrudRepository userCrudRepository;

    @Override
    public void initialize(UniqueUserId constraintAnnotation)
    {}

    @Override
    public boolean isValid(String userId, ConstraintValidatorContext context)
    {
        return !userCrudRepository.existsByUserId(userId);
    }
}
