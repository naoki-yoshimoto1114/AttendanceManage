package com.example.AttendanceManage.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String user_id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String tel;

    @Column
    private String remarks;

    @Column
    private String role;
}
