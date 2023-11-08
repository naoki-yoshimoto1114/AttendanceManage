package com.example.AttendanceManage.Controller.User;

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
        String user_id = "00000";

        // 新規登録処理
        String sql = "UPDATE attendances SET place = ? WHERE user_id = ?";

        try
        {
            jdbcTemplate.update(sql, place, user_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/view";
    }
}
