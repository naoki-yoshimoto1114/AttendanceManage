package com.example.AttendanceManage.Controller.User;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.repositories.historyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    historyRepository repository;

    @GetMapping("history")
    public String index(Model model){
        List<Attendance> list = repository.findAll();
        model.addAttribute("data", list);
        return "user/history";
    }
}
