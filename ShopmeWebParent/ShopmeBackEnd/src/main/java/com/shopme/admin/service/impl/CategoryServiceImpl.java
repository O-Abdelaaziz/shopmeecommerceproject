package com.shopme.admin.service.impl;

import com.shopme.admin.repository.CategoryRepository;
import com.shopme.admin.service.ICategoryService;
import com.shopme.common.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> listCategoriesUsedInForm() {
        List<Category> categoryListForm = new ArrayList<>();
        Iterable<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            if (category.getParent() == null) {
                categoryListForm.add(new Category(category.getName()));

                Set<Category> children = category.getChiidren();

                for (Category subCategory : children) {
                    String name= "--" + subCategory.getName();
                    categoryListForm.add(new Category(name));

                    printChildren(categoryListForm,subCategory, 1);
                }
            }
        }
        return categoryListForm;
    }

    private void printChildren( List<Category> categoryListForm,Category parent, int subLevel) {
        int newSubLevel = subLevel + 1;
        Set<Category> children = parent.getChiidren();

        for (Category subCategory : children) {
            String name = "";
            for (int i = 0; i < newSubLevel; i++) {
                name += "--";
            }
            name+=subCategory.getName();
            categoryListForm.add(new Category(name));
            printChildren(categoryListForm,subCategory, newSubLevel);
        }
    }
}