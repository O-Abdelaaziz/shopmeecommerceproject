package com.shopme.admin.controller;

import com.shopme.admin.security.CustomUserDetails;
import com.shopme.admin.service.IUserService;
import com.shopme.admin.util.FileUploadUtil;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class AccountController {

    private IUserService iUserService;

    @Autowired
    public AccountController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/account")
    public String viewDetails(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        User user = iUserService.getByEmail(email);
        model.addAttribute("user", user);
        return "account/account_form";
    }

    @PostMapping("/account/update")
    public String updateDetails(
            User user,
            @RequestParam(name = "image") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes,
            CustomUserDetails loggedUser) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhoto(fileName);
            User savedUser = iUserService.updateAccount(user);
            String uploadDirectory = "user-photos/" + savedUser.getId();
            FileUploadUtil.cleanDirectory(uploadDirectory);
            FileUploadUtil.saveFile(uploadDirectory, fileName, multipartFile);
        } else {
            if (user.getPhoto().isEmpty()) user.setPhoto(null);
            iUserService.updateAccount(user);
        }
        loggedUser.setFirstName(user.getFirstName());
        loggedUser.setLastName(user.getLastName());

        redirectAttributes.addFlashAttribute("message", "You account details has been updated successfully.");
        return "redirect:/account/account_form";
    }
}
