package com.amr.project.service.impl;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.inserttestdata.repository.ItemRepository;
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
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CartItemMapper cartItemMapper;
    private final CartItemDao cartItemDao;

    @Autowired
    protected CartItemServiceImpl(ItemRepository itemRepository, ItemService itemService, CartItemMapper cartItemMapper, CartItemDao cartItemDao) {
        super(cartItemDao);
        this.itemRepository = itemRepository;
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

    @Override
    public CartItem getAllItem(CartItem cartItem){
        List<Long> list = cartItem.getItems().stream().map(item -> item.getId()).sorted().collect(Collectors.toList());
        List<Item> itemsList = new ArrayList<>();
        for (Long id : list){
            itemsList.add(itemRepository.findById(id).orElse(null));
        }
        CartItem item = new CartItem();
        item.setItems(itemsList);
        return item;
    }


}
