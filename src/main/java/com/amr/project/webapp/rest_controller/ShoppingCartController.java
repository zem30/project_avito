package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/shoppingCart/")
public class ShoppingCartController {

    private final CartItemService cartItemService;
    private final ShopService shopService;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final ShopMapper shopMapper;

    @GetMapping("/getShoppingCart")
    public List<CartItemDto> getShoppingCart(Principal principal) {
        System.out.println(principal);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        if (principal != null) {
            List<CartItem> cartItems = cartItemService.getAll();
            cartItems.forEach(s -> cartItemDtos.add(cartItemMapper.cartItemToDto(s)));
        }
        return cartItemDtos;
    }

    @PostMapping("/addCartItem")
    public ResponseEntity<Void> addCartItem(@RequestBody CartItemDto cartItemDto, Principal principal) {
        if (principal != null) {
            CartItem cartItem = cartItemMapper.dtoToCartItem(cartItemDto);
            cartItem.setShop(shopService.getShop(shopMapper.dtoToShop(cartItemDto.getShopDto()).getName()));
            cartItem.setUser(userService.findByUsername(principal.getName()));
            if(cartItemService.existByUserIdAndItemId(cartItem.getUser().getId(), cartItem.getItem().getId())) {
                CartItem tmpCartItem = cartItemService.getByKey(cartItem.getId());
                cartItem.setQuantity(cartItem.getQuantity() + tmpCartItem.getQuantity());
                cartItemService.update(cartItem);
            } else {
                cartItemService.persist(cartItem);
            }
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCartItem")
    public ResponseEntity<Void> deleteCartItem(@PathVariable @NonNull Long id, Principal principal) {
        System.out.println(principal.getName());
        CartItem item = cartItemService.getByKey(id);
        cartItemService.delete(item);
        return ResponseEntity.ok().build();
    }


}
