package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendanceManage.Entity.Attendance;

import java.util.List;
import java.util.Optional;

@Repository
public interface historyRepository extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByUserIdOrderByDateAsc(String user_id);
}
