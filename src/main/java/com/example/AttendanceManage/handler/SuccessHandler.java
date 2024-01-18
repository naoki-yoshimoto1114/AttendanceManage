package com.example.AttendanceManage.handler;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.repositories.AttendanceCrudRepository;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler
{
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private AttendanceCrudRepository attendanceCrudRepository;

    @Autowired
    private HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        Optional<User> loginUser = userCrudRepository.findByUserId(userId);
        Optional<Attendance> status = attendanceCrudRepository.findByUserIdAndDate(userId, LocalDate.now());

        if(status.isPresent()){
            session.setAttribute("status", status.get().getStatus());
            session.setAttribute("attendance_id", status.get().getAttendanceId());
        }else{
            session.setAttribute("status", "未出勤");
        }
        if(loginUser.isPresent())
        {
            session.setAttribute("userId", loginUser.get().getUserId());
            session.setAttribute("id", loginUser.get().getId());
            session.setAttribute("name", loginUser.get().getName());
            session.setAttribute("department", loginUser.get().getDepartment());
        }

        response.sendRedirect("/");
    }
}
