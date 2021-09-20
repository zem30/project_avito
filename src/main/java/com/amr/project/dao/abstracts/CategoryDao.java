package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Category;

import java.util.List;

public interface CategoryDao extends ReadWriteDao<Category, Long> {
    List<Category> getAllCategory();
    Category getCategory(String nameCategory);
}
