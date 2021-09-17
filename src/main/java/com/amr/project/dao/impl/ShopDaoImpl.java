package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.entity.Shop;
import com.amr.project.util.QueryResultWrapper;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;

@Repository
public class ShopDaoImpl extends ReadWriteDaoImpl<Shop, Long> implements ShopDao {

    @Override
    public Shop getShop(String nameShop) {
        TypedQuery<Shop> category = (TypedQuery<Shop>) entityManager.createQuery("select u from Category u where u.name=:nameShop");
        category.setParameter("nameShop", nameShop);
        return QueryResultWrapper.wrapGetSingleResult(category);
    }
}
