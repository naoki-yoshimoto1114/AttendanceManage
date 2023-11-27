package com.example.AttendanceManage.Controller.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.repositories.AttendanceCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
public class IndexController {
    @Autowired
    AttendanceCrudRepository repository;
    @Autowired
    private HttpSession session;

    @RequestMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model)
    {
        boolean hasRoleAdmin = user.getAuthorities().stream()
                .allMatch(authority -> authority.getAuthority().equals("ADMIN"));
//        model.addAttribute("hasRoleAdmin", hasRoleAdmin);
        String status = (String) session.getAttribute("status");
        model.addAttribute("status", status);
        if(status.equals("勤務中")){
            model.addAttribute("msg1", "休憩開始");
            model.addAttribute("msg2", "退勤");
            model.addAttribute("action", "/workplace");
        }else if(status.equals("未出勤")){
            model.addAttribute("msg1", "出勤");
            model.addAttribute("msg2", "退勤");
            model.addAttribute("action", "/workplace");
        }
        return "user/index";
    }
}
