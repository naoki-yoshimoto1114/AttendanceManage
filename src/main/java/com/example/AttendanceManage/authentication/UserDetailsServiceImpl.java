package com.example.AttendanceManage.authentication;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserCrudRepository userCrudRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User userInfo = userCrudRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        String hp = passwordEncoder.encode(userInfo.getPassword());
        System.out.println(hp);

//        return org.springframework.security.core.userdetails.User.withUsername("user")
//                .password(passwordEncoder.encode("1234"))
//                .roles("USER")
//                .build();
        return org.springframework.security.core.userdetails.User.withUsername(userInfo.getUserId())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .roles("USER")
                .build();
    }
}
