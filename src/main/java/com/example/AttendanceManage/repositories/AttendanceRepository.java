package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
//    public Optional<Attendance> findById(Integer id);
//    public Optional<Attendance> findByDateAndName(String date, String user_id);
}
