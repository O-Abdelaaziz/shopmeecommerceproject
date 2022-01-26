package com.shopme.admin.controller;


import com.shopme.admin.service.ICategoryService;
import com.shopme.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    @GetMapping("/categories/new")
    public String create(Model model){
        model.addAttribute("category",new Category());
        model.addAttribute("pageTitle","add new category");

        List<Category> categoryListForm=iCategoryService.listCategoriesUsedInForm();
        model.addAttribute("categoryListForm",categoryListForm);
        return "categories/category_form";
    }
}
