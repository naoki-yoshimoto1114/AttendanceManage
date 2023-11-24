package com.example.AttendanceManage.Controller.User;

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
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model)
    {
        boolean hasRoleAdmin = user.getAuthorities().stream()
                .allMatch(authority -> authority.getAuthority().equals("ADMIN"));
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);

        Optional<Attendance> data = repository.findById(3);
        model.addAttribute("data", data.get());
        Attendance attendance = data.get();
        String status = attendance.getStatus();
        if(status.equals("勤務中")){
            model.addAttribute("msg1", "休憩開始");
            model.addAttribute("msg2", "退勤");
            model.addAttribute("action", "/workplace");
        }else{
            model.addAttribute("msg1", "出勤");
            model.addAttribute("msg2", "退勤");
            model.addAttribute("action", "/workplace");
        }
        return "user/index";
    }
}
