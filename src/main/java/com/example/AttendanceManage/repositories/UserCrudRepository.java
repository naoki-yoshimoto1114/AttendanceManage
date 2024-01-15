package com.example.AttendanceManage.repositories;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
    /**
     * userIdのユーザのレコードを取得する
     * @param userId ユーザID
     * @return ユーザのレコード
     */
    Optional<User> findByUserId(String userId);

    /**
     * 全てのユーザを取得し、Idの昇順で並べ替えをする
     * @return usersテーブルのすべてのデータ
     */
    List<User> findAllByOrderById();

    /**
     * userIdが存在するか確認する
     * @param userId ユーザID
     * @return 存在していればTrue、存在しなければFalse
     */
    boolean existsByUserId(String userId);

    /**
     * 同じuserIdが存在するか確認する
     * @param userId ユーザID
     * @param id ID
     * @return 存在していればTrue、存在しなければFalse
     */
    boolean existsByUserIdAndIdNot(@Param("userId") String userId, @Param("id") Integer id);

    Page<User> findAllByOrderById(Pageable pageable);
}
