package com.example.AttendanceManage.Controller.Master;

import com.example.AttendanceManage.Entity.User;
import com.example.AttendanceManage.repositories.UserCrudRepository;
import com.example.AttendanceManage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCrudRepository userCrudRepository;

    @RequestMapping("master/users")
    private String index(Model model)
    {
        model.addAttribute("users", userCrudRepository.findAll());
        return "master/index";
    }

    @GetMapping("master/user/add")
    private String add()
    {
        return "master/user_add";
    }

    @PostMapping("master/user/create")
    private String create(@ModelAttribute User user)
    {
        userCrudRepository.save(user);
        return "redirect:/master/users";
    }

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
