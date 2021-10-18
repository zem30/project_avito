package com.amr.project.service.impl;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {

    private final ItemService itemService;
    private final CartItemMapper cartItemMapper;
    private final CartItemDao cartItemDao;

    @Autowired
    protected CartItemServiceImpl(ItemService itemService, CartItemMapper cartItemMapper, CartItemDao cartItemDao) {
        super(cartItemDao);
        this.itemService = itemService;
        this.cartItemMapper = cartItemMapper;
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

    //8888 времменая корзина для не авторизированых пользователей
    @Override
    public CartItemDto temporaryBasket(Long id){
        List<Item> itemList = new ArrayList<>();
        CartItem cartItem = new CartItem();
        if (id != null){
            itemList.add(itemService.getItemId(id));
        } else {
            cartItem.setItems(itemList);
            return cartItemMapper.cartItemToDto(cartItem);
        }
        return null;
    }
}
