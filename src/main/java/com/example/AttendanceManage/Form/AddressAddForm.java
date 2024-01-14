package com.example.AttendanceManage.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddressAddForm
{
    @NotEmpty(message = "メールアドレスは入力必須です。")
    private String email;

    @NotEmpty(message = "電話番号は入力必須です。")
    private String tel;

    private String remarks;
}
