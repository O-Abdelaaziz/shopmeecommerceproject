package com.shopme.admin.service;

import com.shopme.common.entity.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> listAll();
    public List<Category> listCategoriesUsedInForm();

}
