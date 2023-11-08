package com.example.AttendanceManage.Controller.Master;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TotalWorkTimeController {
    @RequestMapping("master/worktime")
    private String index()
    {
        return "master/total_worktime";
    }
}
