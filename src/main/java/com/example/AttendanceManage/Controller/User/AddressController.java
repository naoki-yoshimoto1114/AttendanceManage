package com.example.AttendanceManage.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {
    @GetMapping("address")
    public String index()
    {
        return "user/address";
    }
}
