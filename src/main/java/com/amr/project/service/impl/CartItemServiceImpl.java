package com.amr.project.service.impl;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.dao.abstracts.CartItemDao;
import com.amr.project.inserttestdata.repository.CartItemRepository;
import com.amr.project.inserttestdata.repository.ItemRepository;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl extends ReadWriteServiceImpl<CartItem, Long> implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final CartItemDao cartItemDao;
    private final UserService userService;

    @Autowired
    protected CartItemServiceImpl(CartItemRepository cartItemRepository,
                                  ItemRepository itemRepository,
                                  CartItemDao cartItemDao,
                                  UserService userService) {
        super(cartItemDao);
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
        this.cartItemDao = cartItemDao;
        this.userService = userService;
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
    public List<Item> getAllItem(User user){
        List<Long> list = user.getCartItems().stream().map(item -> item.getId()).sorted().collect(Collectors.toList());
        List<Item> itemsList = new ArrayList<>();
        for (Long id : list){
            itemsList.add(itemRepository.findById(id).orElse(null));
        }
        return itemsList;
    }

    @Override
    public void plusCartItem(Long id){
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        int quantity = cartItem.getQuantity();
        cartItem.setQuantity(quantity + 1);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void minusCartItem(Long id){
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        int quantity = cartItem.getQuantity();
        if (quantity > 0){
            cartItem.setQuantity(quantity - 1);
            cartItemRepository.save(cartItem);
        } else {
            cartItemRepository.delete(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemByUserAuthorized(){
        User user = userService.getUserId(userService.getAuthorized().getId());
        List<CartItem> cartItems = user.getCartItems();
        return cartItems;
    }


}
