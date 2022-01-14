package com.shopme.admin.controller;

import com.shopme.admin.service.IUserService;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Created 13/01/2022 - 18:33
 * @Package com.shopme.admin.controller
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Controller
public class UserController {

    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/users")
    public String index(Model model) {
        List<User> userList = iUserService.listAll();
        model.addAttribute("userList", userList);
        return "users/users";
    }

    @GetMapping("/users/new")
    public String create(Model model) {
        User user = new User();
        user.setEnabled(true);
        model.addAttribute("user", user);

        List<Role> roles = iUserService.listRoles();
        model.addAttribute("roles", roles);
        return "users/user_form";
    }

    @PostMapping("/users")
    public String store(User user, RedirectAttributes redirectAttributes) {
        iUserService.save(user);
        redirectAttributes.addFlashAttribute("username",user.getUsername());
        return "redirect:/users";
    }
}
