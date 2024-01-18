package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceCrudRepository extends CrudRepository<Attendance, Integer> {
    Optional<Attendance> findByUserIdAndDate(String userId, LocalDate date);

}
