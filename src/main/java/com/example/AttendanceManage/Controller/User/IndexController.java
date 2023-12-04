package com.example.AttendanceManage.Controller.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.AttendanceManage.repositories.AttendanceCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {
    @Autowired
    AttendanceCrudRepository repository;
    @Autowired
    private HttpSession session;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index(@AuthenticationPrincipal User user, Model model, HttpSession session)
    {
        String status = (String) session.getAttribute("status");
        boolean rest_flag = (boolean) session.getAttribute("restEnd");
        model.addAttribute("status", status);
        // 管理者or一般
        boolean hasRoleAdmin = user.getAuthorities().stream()
                .allMatch(authority -> authority.getAuthority().equals("ADMIN"));
        session.setAttribute("hasRoleAdmin", hasRoleAdmin);

        if(status.equals("勤務中")){
            if(rest_flag){
                model.addAttribute("msg1", "退勤");
                model.addAttribute("action", "/endWork");
            }else{
                model.addAttribute("msg1", "休憩開始");
                model.addAttribute("msg2", "退勤");
                model.addAttribute("action", "/startRest");
          }
        }else if(status.equals("未出勤") || status.equals("退勤")){
            model.addAttribute("msg1", "出勤");
            model.addAttribute("msg2", "退勤");
            model.addAttribute("action", "/workplace");
        }else if(status.equals("休憩中")) {
            model.addAttribute("msg1", "休憩終了");
            model.addAttribute("msg2", "退勤");
            model.addAttribute("action", "/endRest");
        }
        return "user/index";
    }

    @GetMapping("/startRest")
    public String startRest(@AuthenticationPrincipal User user, Model model){

        Integer attendance_id = (Integer)session.getAttribute("attendance_id");

        Date now = new Date();
        SimpleDateFormat sdfTime = new SimpleDateFormat("H:m:s");
        String rest_start = sdfTime.format(now);

        // 休憩開始時間登録処理
        String sql = "UPDATE attendances SET rest_start = ?::time, status = ? WHERE attendance_id = ?";

        try{
            jdbcTemplate.update(sql, rest_start, "休憩中", attendance_id);
            session.setAttribute("status", "休憩中");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/view";
    }

    @GetMapping("/endRest")
    public String endRest(@AuthenticationPrincipal User user, Model model){

        Integer attendance_id = (Integer)session.getAttribute("attendance_id");

        Date now = new Date();
        SimpleDateFormat sdfTime = new SimpleDateFormat("H:m:s");
        String rest_end = sdfTime.format(now);

        // 休憩開始時間登録処理
        String sql = "UPDATE attendances SET rest_end = ?::time, status = ? WHERE attendance_id = ?";

        try{
            jdbcTemplate.update(sql, rest_end, "勤務中", attendance_id);
            session.setAttribute("status", "勤務中");
            session.setAttribute("restEnd", true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/view";
    }

    @GetMapping("/endWork")
    public String endWork(@AuthenticationPrincipal User user, Model model){

        Integer attendance_id = (Integer)session.getAttribute("attendance_id");

        Date now = new Date();
        SimpleDateFormat sdfTime = new SimpleDateFormat("H:m:s");
        String end_time = sdfTime.format(now);

        // 休憩開始時間登録処理
        String sql = "UPDATE attendances SET end_time = ?::time, status = ? WHERE attendance_id = ?";

        try{
            jdbcTemplate.update(sql, end_time, "退勤", attendance_id);
            session.setAttribute("status", "退勤");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/view";
    }

}
