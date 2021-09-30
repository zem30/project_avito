package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.model.entity.CartItem;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class CartItemDaoImpl extends ReadWriteDaoImpl<CartItem, Long> implements CartItemDao {

    @Override
    public boolean existByUserIdAndItemId(long userId, long itemId) {
        Query query = entityManager.createQuery("SELECT c FROM CartItem c JOIN User u ON u.id = c.user.id JOIN Item i ON i.id = c.item.id WHERE i.id = :itemId AND u.id = :userId");
        query.setParameter("itemId", itemId);
        query.setParameter("userId", userId);
        int count = query.getResultList().size();
        return count > 0;
    }
}
