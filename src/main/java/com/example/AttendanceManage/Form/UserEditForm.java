package com.example.AttendanceManage.Form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserEditForm
{
    @NotEmpty(message = "名前は入力必須です。")
    private String name;

    @NotEmpty(message = "ユーザIDは入力必須です。")
    @Pattern(regexp = "\\d{5}", message = "5桁の数値を入力してください")
    private String userId;

    @NotEmpty(message = "パスワードは入力必須です。")
    private String password;

    @NotNull(message = "選択してください。")
    private String department;

    @NotNull(message = "選択してください。")
    private String role;
}