package com.shopme.admin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Created 12/01/2022 - 13:07
 * @Package com.shopme.admin
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Controller
public class MainController {

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "account/login";
    }

//    @GetMapping("/logout")
//    public String viewLogoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/account/login?logout";
//    }
}
