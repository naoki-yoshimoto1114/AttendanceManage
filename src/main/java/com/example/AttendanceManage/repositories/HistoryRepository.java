package com.example.AttendanceManage.repositories;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendanceManage.Entity.Attendance;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<Attendance, Integer> {

    //public Page<Attendance> findAll(Pageable pageable);
    public Page<Attendance> findByUserIdAndDateBetweenOrderByDateAsc(Pageable pageable, String user_id, LocalDate startDate, LocalDate endDate);
    List<Attendance> findByUserIdOrderByDateDesc(String user_id);
}
