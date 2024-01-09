package com.example.AttendanceManage.Controller;

import com.example.AttendanceManage.util.AppUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private HttpSession session;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("login")
    public String index()
    {
        String status = "未出勤";
        session.setAttribute("status", status);
        return "login";
    }

    @GetMapping(value = "login", params = "error")
    public String indexWithError(Model model)
    {
        String errorMsg = AppUtil.getMessage(messageSource, "login.wrongInput");
        model.addAttribute("errorMsg", errorMsg);
        return "login";
    }
}
