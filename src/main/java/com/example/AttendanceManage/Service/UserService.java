package com.example.AttendanceManage.Service;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.Form.AddressAddForm;
import com.example.AttendanceManage.Form.UserAddForm;
import com.example.AttendanceManage.Form.UserEditForm;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
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

    @Transactional
    public void updateUser(UserEditForm form, Integer id)
    {
        Optional<User> optionalUser = userCrudRepository.findById(id);
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            user.setName(form.getName());
            user.setUserId(form.getUserId());
            String hashedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(hashedPassword);
            user.setRole(form.getRole());
            user.setDepartment(form.getDepartment());

            userCrudRepository.save(user);
        }
    }
    @Transactional
    public void updateAddress(AddressAddForm form, Integer id)
    {
        Optional<User> optionalUser = userCrudRepository.findById(id);
        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            user.setEmail(form.getEmail());
            user.setTel(form.getTel());
            user.setRemarks(form.getRemarks());

            userCrudRepository.save(user);
        }
    }

}
