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
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model, HttpSession session)
    {
        // 管理者or一般
        boolean hasRoleAdmin = user.getAuthorities().stream()
                .allMatch(authority -> authority.getAuthority().equals("ADMIN"));
        session.setAttribute("hasRoleAdmin", hasRoleAdmin);

        // TODO:ログインしたユーザIDで取得
        Optional<Attendance> data = repository.findById(1);
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
