package com.example.AttendanceManage.Controller.User;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.Enum.Status;
import com.example.AttendanceManage.repositories.AttendanceCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
public class IndexController {
    @Autowired
    AttendanceCrudRepository repository;
    @RequestMapping("/")
    public String index(Model model)
    {
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
