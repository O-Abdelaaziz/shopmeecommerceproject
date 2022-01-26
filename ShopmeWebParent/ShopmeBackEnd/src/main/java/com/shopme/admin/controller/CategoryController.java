package com.shopme.admin.controller;


import com.shopme.admin.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    private ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("/categories")
    public String index(Model model) {
        model.addAttribute("categories", iCategoryService.listAll());
        return "categories/categories";
    }
}
