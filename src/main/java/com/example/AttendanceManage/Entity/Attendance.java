package com.example.AttendanceManage.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "attendances")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private int attendanceId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "begin_time")
    private LocalTime beginTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "rest_start")
    private LocalTime restStart;

    @Column(name = "rest_end")
    private LocalTime restEnd;

    @Column(name = "working_time")
    private LocalTime workingTime;

    @Column(name = "rest_time")
    private LocalTime restTime;

    @Column
    private String place;

    @Column
    private LocalDate date;

    @Column
    private String status;

    public void calcRestTime() {
        Duration duration = Duration.between(restStart, restEnd);
        LocalTime lt = LocalTime.MIDNIGHT.plus(duration); //表示形式変更
        setRestTime(lt);
    }


    public void calcWorkingTime() {
        Duration duration = Duration.between(beginTime, endTime);
        if(restTime != null) {
            duration = duration.minus(Duration.between(restStart, restEnd));
        }
        LocalTime lt = LocalTime.MIDNIGHT.plus(duration);

        setWorkingTime(lt);
    }
}
