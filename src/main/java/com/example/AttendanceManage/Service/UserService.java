package com.example.AttendanceManage.Service;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.Form.UserAddForm;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(UserAddForm form)
    {
        User user = new User();
        user.setName(form.getName());
        user.setUserId(form.getUserId());
        String hashedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(hashedPassword);
        user.setRole(form.getRole());
        user.setDepartment(form.getDepartment());

        userCrudRepository.save(user);
    }
}
