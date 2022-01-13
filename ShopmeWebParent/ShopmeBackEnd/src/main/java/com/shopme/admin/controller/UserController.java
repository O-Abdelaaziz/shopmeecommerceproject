package com.shopme.admin.controller;

import com.shopme.admin.service.IUserService;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String viewUserListPage(Model model) {
        List<User> userList = iUserService.listAll();
        model.addAttribute("userList", userList);
        return "users/users";
    }
}
