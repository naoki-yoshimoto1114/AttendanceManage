package com.example.AttendanceManage.Controller.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model, HttpSession session)
    {
        boolean hasRoleAdmin = user.getAuthorities().stream()
                .allMatch(authority -> authority.getAuthority().equals("ADMIN"));
        session.setAttribute("hasRoleAdmin", hasRoleAdmin);
        model.addAttribute("hasRoleAdmin", hasRoleAdmin);
        return "user/index";
    }
}
