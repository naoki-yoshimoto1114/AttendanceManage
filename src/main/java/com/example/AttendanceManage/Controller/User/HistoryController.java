package com.example.AttendanceManage.Controller.User;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.Service.AttendanceService;
import com.example.AttendanceManage.repositories.HistoryRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    HistoryRepository repository;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private HttpSession session;

    @Transactional
    @GetMapping("history")
    public String index(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC,
            sort = {"date"}) Pageable pageable, Model model){
        Page<Attendance> historyPage = attendanceService.getAttendances(pageable);

        String user_id = (String)session.getAttribute("userId");
        List<Attendance> list = repository.findByUserIdOrderByDateAsc(user_id);

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
        model.addAttribute("page", historyPage);
        model.addAttribute("History", historyPage.getContent());

        return "user/history";
    }
}
