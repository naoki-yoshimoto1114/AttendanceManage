package com.example.AttendanceManage.Controller.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HttpSession session;

    @GetMapping("view")
    public String index(Model model)
    {
        // 一覧表示
        String sql = "SELECT * FROM attendances";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        model.addAttribute("attendances", result);
        return "user/view";
    }

    @PostMapping("view")
    public String updatePlace(@RequestParam("place") String place)
    {
//        String user_id = "00000";
        String user_id = (String) session.getAttribute("userId");

        Date now = new Date();

        SimpleDateFormat sdfTime = new SimpleDateFormat("H:m:s");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");

        String begin_time = sdfTime.format(now);
        String date = sdfDate.format(now);

        // 新規登録処理
        String sql = "INSERT INTO attendances (user_id, begin_time, end_time, rest_start, rest_end, place, date, status)" +
                " VALUES(?, ?::time, ?, ?, ?, ?, ?::date, ?)";
        // attendance_id取得
        String id = "SELECT MAX(attendance_id) from attendances";

        try
        {
            jdbcTemplate.update(sql, user_id, begin_time, null, null, null, place, date, "勤務中");
            Integer attendance_id = jdbcTemplate.queryForObject(id, Integer.class);
            session.setAttribute("status", "勤務中1");
            session.setAttribute("attendance_id", attendance_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/view";
    }
}
