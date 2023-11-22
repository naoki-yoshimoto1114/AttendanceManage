package com.example.AttendanceManage.authentication;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserCrudRepository userCrudRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User userInfo = userCrudRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User.withUsername(userInfo.getUserId())
                .password(userInfo.getPassword())
//                .roles(userInfo.getRole())
                .authorities(userInfo.getRole())
                .build();

    }
}
