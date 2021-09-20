package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {
    private final CartItemDao cartItemDao;

    protected CartItemServiceImpl(ReadWriteDao<CartItem, Long> dao, CartItemDao cartItemDao) {
        super(dao);
        this.cartItemDao = cartItemDao;
    }

    @Override
    public List<CartItem> getAllCartItem() {
        return cartItemDao.getAllCartItem();
    }
}
