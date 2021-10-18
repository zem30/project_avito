package com.amr.project.webapp.rest_controller;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.service.abstracts.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasketRestController {

    private final CartItemService cartItemService;

    public BasketRestController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping("basket")
    public ResponseEntity setCartItem(@RequestBody Long id){
        cartItemService.temporaryBasket(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("basket")
    public CartItemDto getCartItem(){
        return cartItemService.temporaryBasket(null);
    }
}
