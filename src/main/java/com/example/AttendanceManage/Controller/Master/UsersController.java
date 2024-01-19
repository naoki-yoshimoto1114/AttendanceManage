package com.example.AttendanceManage.Controller.Master;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.Form.UserAddForm;
import com.example.AttendanceManage.Form.UserEditForm;
import com.example.AttendanceManage.Service.UserService;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import com.example.AttendanceManage.repositories.UserRepository;
import com.example.AttendanceManage.util.AppUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private HttpSession session;


    @GetMapping("master/users")
    private String index(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model)
    {
        Page<User> userPage = userCrudRepository.findAllByOrderById(pageable);
        model.addAttribute("page", userPage);
        model.addAttribute("users", userPage.getContent());
        return "master/index";
    }

    @GetMapping("master/user/add")
    private String add(Model model, UserAddForm userAddForm)
    {
        model.addAttribute("userAddForm", userAddForm);
        return "master/user_add";
    }

    @PostMapping("master/user/add")
    private String addUser(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("userAddForm") @Validated UserAddForm userAddForm, BindingResult bindingResult)
    {
        // バリデーションエラーあり
        if(bindingResult.hasErrors())
        {
            return "master/user_add";
        }
        // ユーザ登録
        userService.addUser(userAddForm);

        String msg = "ユーザを新規登録しました";
        redirectAttributes.addFlashAttribute("msg", msg);

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
    private String updateUser(User user, Model model, RedirectAttributes redirectAttributes, @ModelAttribute("userEditForm") @Validated UserEditForm userEditForm, BindingResult bindingResult)
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
            String errorMsg = AppUtil.getMessage(messageSource, "validate.existsUserId");
            model.addAttribute("userIdErrMsg", errorMsg);
            return "/master/user_edit";
        }

        // 更新処理
        userService.updateUser(userEditForm, user.getId());

        // session再生成(自信の情報が変更された場合の対応)
        session.setAttribute("userId", userEditForm.getUserId());
        session.setAttribute("name", userEditForm.getName());
        session.setAttribute("department", userEditForm.getDepartment());
        session.setAttribute("role", userEditForm.getRole());

        String msg = "ユーザ情報を編集しました";
        redirectAttributes.addFlashAttribute("msg", msg);

        return "redirect:/master/users";
    }

    @PostMapping("master/user/delete")
    private String delete(RedirectAttributes redirectAttributes, @ModelAttribute User user)
    {
        userCrudRepository.delete(user);

        String msg = "ユーザを削除しました";
        redirectAttributes.addFlashAttribute("msg", msg);

        return "redirect:/master/users";
    }

    @GetMapping("master/user/address/{id}")
    private String address(@PathVariable Integer id, Model model)
    {
        Optional<User> user = userCrudRepository.findById(id);
        user.ifPresent(value -> model.addAttribute("user", value));
        return "master/address";
    }
}
