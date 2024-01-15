package com.example.AttendanceManage.Service;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class AttendanceService {
    @Autowired
    private HistoryRepository historyRepository;

    public Page<Attendance> getAttendances(Pageable pageable, String user_id, LocalDate startDate, LocalDate endDate){
        return historyRepository.findByUserIdAndDateBetweenOrderByDateAsc(pageable, user_id, startDate, endDate);
    }
}
