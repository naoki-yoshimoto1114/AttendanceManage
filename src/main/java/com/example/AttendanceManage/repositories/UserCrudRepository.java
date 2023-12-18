package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserId(String userId);
    List<User> findAllByOrderById();
    boolean existsByUserId(String userId);
}
