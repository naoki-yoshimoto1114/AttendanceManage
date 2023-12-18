package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.Attendance;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceCrudRepository extends CrudRepository<Attendance, Integer> {
}
