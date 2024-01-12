package com.example.AttendanceManage.Service;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class AttendanceService {
    @Autowired
    private HistoryRepository historyRepository;

    public Page<Attendance> getAttendances(Pageable pageable, String user_id){
        return historyRepository.findByUserIdOrderByDateDesc(pageable, user_id);
    }
}
