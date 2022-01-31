package com.shopme.admin.controller;


import com.shopme.admin.service.ICategoryService;
import com.shopme.admin.util.FileUploadUtil;
import com.shopme.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
    public String create(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "add new category");

        List<Category> categoryListForm = iCategoryService.listCategoriesUsedInForm();
        model.addAttribute("categoryListForm", categoryListForm);
        return "categories/category_form";
    }

    @PostMapping("/categories/store")
    public String store(Category category, @RequestParam("fileImage") MultipartFile fileImage, RedirectAttributes redirectAttributes) throws IOException {
        String fileName = StringUtils.cleanPath(fileImage.getOriginalFilename());
        category.setImage(fileName);

        Category savedCategory = iCategoryService.saveCategory(category);
        String uploadDir = "../category-images/" + savedCategory.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, fileImage);
        redirectAttributes.addFlashAttribute("message", "Category created successfully.");
        return "redirect:/categories";
    }
}
