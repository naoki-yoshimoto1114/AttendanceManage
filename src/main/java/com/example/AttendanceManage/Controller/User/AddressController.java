package com.example.AttendanceManage.Controller.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {
        @Autowired
        private JdbcTemplate jdbcTemplate;
        private HttpSession session;
        @RequestMapping("/address")
        private String getContact(){return "user/address";}
        @PostMapping("/Contact")
        public String addContact(
            @RequestParam String email,
            @RequestParam String tel,
            @RequestParam String note){
        Integer attendance_id = (Integer)session.getAttribute("attendance_id");
        String sql = "UPDATE address SET tel = ?, email = ?, note = ? WHERE attendance_id = ?";
        try {
                jdbcTemplate.update(sql, email, tel, note);
        }catch (Exception e) {
                e.printStackTrace();
        }
        return "user/address";
    }
}
