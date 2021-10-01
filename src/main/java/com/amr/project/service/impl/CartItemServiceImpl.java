package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {

    private final CartItemDao cartItemDao;

    @Autowired
    protected CartItemServiceImpl(CartItemDao cartItemDao) {
        super(cartItemDao);
        this.cartItemDao = cartItemDao;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existByUserIdAndItemId(long userId, long itemId) {
        return cartItemDao.existByUserIdAndItemId(userId, itemId);
    }

    @Override
    @Transactional(readOnly = true)
    public CartItem getByUserIdAndItemId(long userId, long itemId) {
        return cartItemDao.getByUserIdAndItemId(userId, itemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItem> getAllByUser(User user) {
        return cartItemDao.getAllByUser(user);
    }
}
