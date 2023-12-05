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

        for(Attendance a: list){
            //既にデータがある場合の判定処理を加える 未実装


            if(a.getRestStart() != null && a.getRestEnd() != null){
                a.calcRestTime(); //休憩時間の算出
            }
            if(a.getBeginTime() != null && a.getEndTime() != null){
                a.calcWorkingTime(); //労働時間の算出
            }
        }

        //登録処理?　未実装

        model.addAttribute("data", list);

        return "user/history";
    }
}
