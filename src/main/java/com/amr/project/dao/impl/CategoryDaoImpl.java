package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.model.entity.Category;
import com.amr.project.util.QueryResultWrapper;
import com.amr.project.util.Util;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;

@Repository
public class CategoryDaoImpl extends ReadWriteDaoImpl<Category, Long> implements CategoryDao {

    @Override
    public Category getCategory(String nameCategory) {
        TypedQuery<Category> category = (TypedQuery<Category>) entityManager.createQuery("select u from Category u where u.name=:nameCategory");
        category.setParameter("nameCategory", nameCategory);
        return QueryResultWrapper.wrapGetSingleResult(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        TypedQuery<Category> category = (TypedQuery<Category>) entityManager.createQuery("select u from Category u where u.id=:id");
        category.setParameter("id", id);
        return QueryResultWrapper.wrapGetSingleResult(category);
    }



}
