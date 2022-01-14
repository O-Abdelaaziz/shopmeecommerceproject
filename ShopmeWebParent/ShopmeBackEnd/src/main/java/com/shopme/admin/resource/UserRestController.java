package com.shopme.admin.resource;

import com.shopme.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created 14/01/2022 - 10:41
 * @Package com.shopme.admin.resource
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@RestController
public class UserRestController {

    private IUserService iUserService;

    @Autowired
    public UserRestController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/users/check-email")
    public String checkDuplicateEmail(@Param("id") Long id, @Param("email") String email) {
        return iUserService.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }
}
