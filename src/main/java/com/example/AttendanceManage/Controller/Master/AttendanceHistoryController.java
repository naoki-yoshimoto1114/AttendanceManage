package com.example.AttendanceManage.Controller.Master;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controllercd 
public class AttendanceHistoryController {
    @RequestMapping("master/history")
    private String index()
    {
        return "master/attendance_history";
    }
}
