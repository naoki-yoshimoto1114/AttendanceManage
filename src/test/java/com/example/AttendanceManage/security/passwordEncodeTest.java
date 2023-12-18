package com.example.AttendanceManage.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class passwordEncodeTest
{
    @Test
    public void testPasswordEncoder() {
        // パスワードエンコーダーのインスタンスを作成
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // オリジナルのパスワード
        String originalPassword = "password123";

        // パスワードをエンコード
        String encodedPassword = passwordEncoder.encode(originalPassword);

        // デバッグ表示
        System.out.println("Original Password: " + originalPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
