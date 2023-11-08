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
        String user_id = "00000";
        Date now = new Date();

        SimpleDateFormat sdfTime = new SimpleDateFormat("H:m:s");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");

        String begin_time = sdfTime.format(now);
        String date = sdfDate.format(now);

        // 新規登録処理
        String sql = "INSERT INTO attendances (user_id, begin_time, end_time, rest_start, rest_end, place, date, status)" +
                " VALUES(?, ?::time, ?, ?, ?, ?, ?::date, ?)";

        try
        {
            jdbcTemplate.update(sql, user_id, begin_time, null, null, null, null, date, 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/workplace";
    }
}
