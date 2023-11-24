package com.example.AttendanceManage.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WorkPlaceController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("workplace")
    public String index()
    {
        return "user/workplace";
    }

    @PostMapping("workplace")
    public String insertAttendance()
    {
        return "redirect:/workplace";
    }
}
