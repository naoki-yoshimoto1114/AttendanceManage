package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
}
