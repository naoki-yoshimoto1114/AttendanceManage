package com.example.AttendanceManage.Controller.User;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.repositories.historyRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    @GetMapping("history")
    public String index(Model model){
        List<Attendance> list = repository.findAll();

        for(Attendance a: list){

            if(a.getRestTime() == null){
                if(a.getRestStart() != null && a.getRestEnd() != null){
                    a.calcRestTime(); //休憩時間の算出
                    repository.saveAndFlush(a);
                }
            }

            if(a.getWorkingTime() == null){
                if(a.getBeginTime() != null && a.getEndTime() != null){
                    a.calcWorkingTime(); //労働時間の算出
                    repository.saveAndFlush(a);
                }
            }

        }

        model.addAttribute("data", list);

        return "user/history";
    }
}
