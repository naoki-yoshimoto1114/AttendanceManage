package com.example.AttendanceManage.Controller.User;

import com.example.AttendanceManage.Entity.Attendance;
import com.example.AttendanceManage.Service.AttendanceService;
import com.example.AttendanceManage.repositories.HistoryRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    HistoryRepository repository;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private HttpSession session;

    @Transactional
    @GetMapping("history")
    public String index(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC,
            sort = {"date"}) Pageable pageable,
                        @RequestParam(name = "year", required = false) String year,
                        @RequestParam(name = "month", required = false) String month,
                        Model model)
    {
        String user_id = (String)session.getAttribute("userId");
        List<Attendance> list = repository.findByUserIdOrderByDateDesc(user_id);

        int intYear = 0;
        int intMonth = 0;
        boolean getParamErr = false;
        // 初期表示は現在の月の情報
        if(year != null && month != null)
        {
            if(year.matches("^[0-9]{4}$")) {
                intYear = Integer.parseInt(year);
            } else {
                getParamErr = true;
            }
            if(month.matches("[1-9]|1[0-2]")) {
                intMonth = Integer.parseInt(month);
            } else {
                getParamErr = true;
            }
        }
        else
        {
            // 現在の年を取得
            intYear = LocalDate.now().getYear();
            // 現在の月を取得
            intMonth = LocalDate.now().getMonthValue();
        }

        // getパラメータにエラーがあった場合
        if(getParamErr)
        {
            // 現在の年を取得
            intYear = LocalDate.now().getYear();
            // 現在の月を取得
            intMonth = LocalDate.now().getMonthValue();
        }


        // 指定した年月の最初の日を取得
        LocalDate startDate = LocalDate.of(intYear, intMonth, 1);
        // 指定した年月の最後の日を取得
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());


        //ページネーション処理
        Page<Attendance> historyPage = attendanceService.getAttendances(pageable, user_id, startDate, endDate);

        for(Attendance a: list){

            if(a.getRestTime() == null){
                if(a.getRestStart() != null && a.getRestEnd() != null){
                    a.calcRestTime(); //休憩時間の算出
                    repository.saveAndFlush(a);
                }
            }

            if(a.getWorkingTime() == null){
                if(a.getBeginTime() != null && a.getEndTime() != null){
                    a.calcWorkingTime(); //労働時間の算出
                    repository.saveAndFlush(a);
                }
            }
        }

        // GETリクエストパラメータを設定
        String previousYearMonth = "?year=" + intYear + "&month=" + (intMonth - 1);
        String nextYearMonth = "?year=" + intYear + "&month=" + (intMonth + 1);
        if(intMonth == 12)
        {
            nextYearMonth = "?year=" + (intYear + 1) + "&month=1";
        }
        if(intMonth == 1)
        {
            previousYearMonth = "?year=" + (intYear - 1) + "&month=12";
        }

        model.addAttribute("data", list);
        model.addAttribute("page", historyPage);
        model.addAttribute("History", historyPage.getContent());
        model.addAttribute("year", intYear);
        model.addAttribute("month", intMonth);
        model.addAttribute("previousYearMonth", previousYearMonth);
        model.addAttribute("nextYearMonth", nextYearMonth);

        return "user/history";
    }
}
