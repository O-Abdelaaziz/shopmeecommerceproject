package com.shopme.admin.category;

import com.shopme.admin.repository.CategoryRepository;
import com.shopme.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTest {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Test
    public void testCreateRootCategory() {
        Category category = new Category("Electronics");
        Category savedCategory = categoryRepository.save(category);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSubCategory() {
        Category parent = new Category(5L);
        Category subCategoryCameras = new Category("Memory", parent);
        categoryRepository.saveAll(List.of(subCategoryCameras));
    }

    @Test
    public void testGetCreateCategory() {
        Category category = categoryRepository.findById(2L).get();
        System.out.println("Category Name: " + category.getName());
        Set<Category> children = category.getChiidren();
        children.forEach(child -> System.out.println("Children: " + child.getName()));
        assertThat(children.size()).isGreaterThan(0);
    }

    @Test
    public void testPrintHierarchicalCategories() {
        Iterable<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            if (category.getParent() == null) {
                System.out.println(category.getName());

                Set<Category> children = category.getChiidren();

                for (Category subCategory : children) {
                    System.out.println("--" + subCategory.getName());
                    printChildren(subCategory, 1);
                }
            }
        }
    }

    private void printChildren(Category parent, int subLevel) {
        int newSubLevel = subLevel + 1;
        Set<Category> children = parent.getChiidren();

        for (Category subCategory : children) {
            for (int i = 0; i < newSubLevel; i++) {
                System.out.println("--");
            }
            System.out.println(subCategory.getName());
            printChildren(subCategory, newSubLevel);
        }
    }
}
