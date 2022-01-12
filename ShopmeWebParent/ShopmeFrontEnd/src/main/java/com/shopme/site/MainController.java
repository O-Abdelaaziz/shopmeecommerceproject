package com.shopme.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Created 12/01/2022 - 13:35
 * @Package com.shopme.site
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
}
