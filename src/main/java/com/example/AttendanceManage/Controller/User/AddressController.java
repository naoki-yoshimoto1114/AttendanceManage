package com.example.AttendanceManage.Controller.User;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.Form.AddressAddForm;
import com.example.AttendanceManage.Service.UserService;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AddressController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserCrudRepository userCrudRepository;


    @GetMapping("address/add")
    private String add(Model model, AddressAddForm addressAddForm)
    {
        int id = (Integer)session.getAttribute("id");
        Optional<User> user = userCrudRepository.findById(id);
        if(user.isPresent())
        {
            addressAddForm.setEmail(user.get().getEmail());
            addressAddForm.setTel(user.get().getTel());
            addressAddForm.setRemarks(user.get().getRemarks());
            model.addAttribute("addressAddForm", addressAddForm);
            model.addAttribute("user", user.get());
        }
        return "user/address_add";
    }

    @PostMapping("address/add")
    private String addAddress(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("addressAddForm") @Validated AddressAddForm addressAddForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "user/address_add";
        }
        int userId = (Integer)session.getAttribute("id");
        userService.updateAddress(addressAddForm, userId);

        String msg = "連絡先を登録しました！";
        redirectAttributes.addFlashAttribute("msg", msg);
        // 連絡先保存処理が終わったらどこにリダイレクトする？
        return "redirect:/";
    }

}
