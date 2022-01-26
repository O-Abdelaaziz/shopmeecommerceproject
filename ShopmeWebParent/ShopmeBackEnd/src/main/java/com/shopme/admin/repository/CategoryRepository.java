package com.shopme.admin.repository;

import com.shopme.common.entity.Category;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Lang> {
}
