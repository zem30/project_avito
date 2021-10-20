package com.amr.project.webapp.rest_controller;

import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartItemRestController {

    private final CartItemService cartItemService;

    public CartItemRestController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("/cart-item")
    public CartItem getCartItems(@RequestBody CartItem cartItem){
        CartItem item = cartItemService.getAllItem(cartItem);
        return item;
    }

}
