package com.example.AttendanceManage.Controller.Master;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.Form.UserAddForm;
import com.example.AttendanceManage.Service.UserService;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import com.example.AttendanceManage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping("master/user/create")
//    private String create(@ModelAttribute User user, Model model)
//    {
//        if(userCrudRepository.existsByUserId(user.getUserId()) || Objects.equals(user.getUserId(), ""))
//        {
//            String errMsg = "有効なユーザIDを入力してください。";
//            model.addAttribute("errMsg", errMsg);
//            return "master/user_add";
//        }
//        String hashedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);
//        userCrudRepository.save(user);
//        return "redirect:/master/users";
//    }

    @GetMapping("master/user/edit/{id}")
    private String edit(@PathVariable Integer id, Model model)
    {
        Optional<User> user = userCrudRepository.findById(id);
        if(user.isPresent())
        {
            model.addAttribute("user", user.get());
        }
        return "master/user_edit";
    }

    @PostMapping("master/user/update/{id}")
    private String update(@ModelAttribute User user)
    {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.update(user);
        return "redirect:/master/users";
    }

    @PostMapping("master/user/delete")
    private String delete(@ModelAttribute User user)
    {
        userCrudRepository.delete(user);
        return "redirect:/master/users";
    }
}
