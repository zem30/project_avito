package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.impl.CategoryDaoImpl;
import com.amr.project.model.entity.Category;
import com.amr.project.service.abstracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl extends ReadWriteServiceImpl<Category, Long> implements CategoryService {

    private CategoryDaoImpl categoryDao;

    @Autowired
    public CategoryServiceImpl(ReadWriteDao<Category, Long> dao, CategoryDaoImpl categoryDao) {
        super(dao);
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public Category getCategory(String nameCategory) {
        return categoryDao.getCategory(nameCategory);
    }

}
