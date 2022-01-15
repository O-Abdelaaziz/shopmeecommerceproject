package com.shopme.admin.controller;

import com.shopme.admin.exception.UserNotFoundException;
import com.shopme.admin.service.IUserService;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("pageTitle", "Create New User");

        return "users/user_form";
    }

    @PostMapping("/users")
    public String store(User user, RedirectAttributes redirectAttributes) {
        iUserService.save(user);
        redirectAttributes.addFlashAttribute("message", "The User with email: <strong>" + user.getEmail() + "</strong> has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAttributes) throws UserNotFoundException {
        try {
            User user = iUserService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            List<Role> roles = iUserService.listRoles();
            model.addAttribute("roles", roles);
            return "users/user_form";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/{id}")
    public String delete(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) throws UserNotFoundException {
        try {
            iUserService.delete(id);
            redirectAttributes.addFlashAttribute("message", "user was deleted successfully");
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/users";
    }
}
