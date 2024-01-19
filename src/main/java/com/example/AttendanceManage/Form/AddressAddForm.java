package com.example.AttendanceManage.Form;

import com.example.AttendanceManage.Validator.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddressAddForm
{
    @Email(message = "有効なメールアドレスを入力してください。")
    private String email;

    @PhoneNumber
    private String tel;

    private String remarks;
}
