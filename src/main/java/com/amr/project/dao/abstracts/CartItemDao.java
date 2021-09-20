package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.CartItem;

import java.util.List;

public interface CartItemDao extends ReadWriteDao<CartItem, Long> {
    List<CartItem> getAllCartItem();
}
