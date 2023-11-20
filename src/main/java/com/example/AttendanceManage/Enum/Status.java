package com.example.AttendanceManage.Enum;


public class Status{
    public String getStatusName(int code){
        switch (code) {
            case 1:
                return "未出勤";
            case 2:
                return "勤務中";
            case 3:
                return  "休憩中";
            case 4:
                return "退勤";
            default:
                return "unknown";
        }
    }
}

