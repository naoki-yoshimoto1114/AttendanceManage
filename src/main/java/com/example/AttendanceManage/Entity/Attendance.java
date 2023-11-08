package com.example.AttendanceManage.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "attendances")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int attendance_id;

    @Column(length = 5, nullable = false)
    private String user_id;

    @Column
    private LocalTime begin_time;

    @Column
    private LocalTime end_time;

    @Column
    private LocalTime rest_start;

    @Column
    private LocalTime rest_end;

    @Column(length = 50, nullable = true)
    private String place;

    @Column
    private LocalDate date;

    @Column
    private int status;

    public int getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(int attendance_id) {
        this.attendance_id = attendance_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LocalTime getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(LocalTime begin_time) {
        this.begin_time = begin_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public LocalTime getRest_start() {
        return rest_start;
    }

    public void setRest_start(LocalTime rest_start) {
        this.rest_start = rest_start;
    }

    public LocalTime getRest_end() {
        return rest_end;
    }

    public void setRest_end(LocalTime rest_end) {
        this.rest_end = rest_end;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
