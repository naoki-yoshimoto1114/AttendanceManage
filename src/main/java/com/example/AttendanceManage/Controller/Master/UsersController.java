package com.example.AttendanceManage.Controller.Master;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.Form.UserAddForm;
import com.example.AttendanceManage.Form.UserEditForm;
import com.example.AttendanceManage.Service.UserService;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import com.example.AttendanceManage.repositories.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @RequestMapping("master/users")
    private String index(Model model)
    {
        model.addAttribute("users", userCrudRepository.findAllByOrderById());
        return "master/index";
    }

    @GetMapping("master/user/add")
    private String add(Model model, UserAddForm userAddForm)
    {
        model.addAttribute("userAddForm", userAddForm);
        return "master/user_add";
    }

    @PostMapping("master/user/add")
    private String addUser(Model model, @ModelAttribute("userAddForm") @Validated UserAddForm userAddForm, BindingResult bindingResult)
    {
        // バリデーションエラーあり
        if(bindingResult.hasErrors())
        {
            return "master/user_add";
        }
        // ユーザ登録
        userService.addUser(userAddForm);
        return "redirect:/master/users";
    }

    @GetMapping("master/user/edit/{id}")
    private String edit(@PathVariable Integer id, Model model)
    {
        Optional<User> user = userCrudRepository.findById(id);
        if(user.isPresent())
        {
            UserEditForm userEditForm = new UserEditForm();
            userEditForm.setUserId(user.get().getUserId());
            userEditForm.setName(user.get().getName());
            userEditForm.setRole(user.get().getRole());
            userEditForm.setDepartment(user.get().getDepartment());
            model.addAttribute("userEditForm", userEditForm);
            model.addAttribute("user", user.get());
        }
        return "master/user_edit";
    }

    @PostMapping("master/user/update")
    private String updateUser(User user, Model model, @ModelAttribute("userEditForm") @Validated UserEditForm userEditForm, BindingResult bindingResult)
    {
        // バリデーションエラーあり
        if(bindingResult.hasErrors())
        {
            return "/master/user_edit";
        }

        // userIdの重複チェック
        if(userCrudRepository.existsByUserIdAndIdNot(userEditForm.getUserId(), user.getId()))
        {
            // 重複あり
            model.addAttribute("userIdErrMsg", "このユーザIDは既に登録されています。");
            return "/master/user_edit";
        }

        // 更新処理
        userService.updateUser(userEditForm, user.getId());
        return "redirect:/master/users";
    }

    @PostMapping("master/user/delete")
    private String delete(@ModelAttribute User user)
    {
        userCrudRepository.delete(user);
        return "redirect:/master/users";
    }
}
