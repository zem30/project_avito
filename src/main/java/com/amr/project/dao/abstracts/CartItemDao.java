package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.CartItem;

public interface CartItemDao extends ReadWriteDao<CartItem, Long> {

    public boolean existByUserIdAndItemId(long userId, long itemId);
}
