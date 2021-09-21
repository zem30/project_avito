package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl extends ReadWriteServiceImpl<Category, Long> implements CategoryService {
    private final CategoryDao categoryDao;

    protected CategoryServiceImpl(ReadWriteDao<Category, Long> dao, CategoryDao categoryDao) {
        super(dao);
        this.categoryDao = categoryDao;
    }

    @Transactional
    public Category getCategory(String nameCategory) {
        return categoryDao.getCategory(nameCategory);
    }

}