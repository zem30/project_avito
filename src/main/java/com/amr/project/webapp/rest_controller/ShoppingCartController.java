package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.service.abstracts.CartItemService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/shoppingCart/")
public class ShoppingCartController {

    private final CartItemService cartItemService;
    private final ShopService shopService;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final ShopMapper shopMapper;

    @GetMapping("/getUserShoppingCart")
    public List<CartItemDto> getShoppingCart(Principal principal) {
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        if (principal != null) {
            List<CartItem> cartItems = cartItemService.getAllByUser(userService.findByUsername(principal.getName()));
            if (cartItems != null) {
                cartItems.forEach(cartItem -> cartItemDtos.add(cartItemMapper.cartItemToDto(cartItem)));
            }
        }
        return cartItemDtos;
    }

    @PostMapping("/loadLocalShoppingCartToServer")
    public ResponseEntity<Void> loadLocalShoppingCartToServer(@RequestBody Set<CartItemDto> localCart, Principal principal) {
        if (principal != null && userService.findByUsername(principal.getName()).getCart().size() == 0) {
            localCart.forEach(cartItemDto -> {
                CartItem cartItem = cartItemMapper.dtoToCartItem(cartItemDto);
                cartItem.setShop(shopService.getShop(shopMapper.dtoToShop(cartItemDto.getShopDto()).getName()));
                cartItem.setUser(userService.findByUsername(principal.getName()));
                List<CartItem> cart = cartItem.getUser().getCart();
                cart.add(cartItem);
                cartItem.getUser().setCart(cart);
                cartItemService.persist(cartItem);
            });
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addNewCartItemsAndUpdateQuantity")
    public ResponseEntity<Void> addNewCartItemsAndUpdateQuantity(@RequestBody CartItemDto cartItemDto, Principal principal) {
        if (principal != null) {
            CartItem cartItem = cartItemMapper.dtoToCartItem(cartItemDto);
            cartItem.setShop(shopService.getShop(shopMapper.dtoToShop(cartItemDto.getShopDto()).getName()));
            cartItem.setUser(userService.findByUsername(principal.getName()));
            if (cartItemService.existByUserIdAndItemId(cartItem.getUser().getId(), cartItem.getItem().getId())) {
                CartItem tmpCartItem = cartItemService.getByUserIdAndItemId(cartItem.getUser().getId(), cartItem.getItem().getId());
                tmpCartItem.setQuantity(cartItem.getQuantity());
                cartItemService.update(tmpCartItem);
            } else {
                List<CartItem> cart = cartItem.getUser().getCart();
                cart.add(cartItem);
                cartItem.getUser().setCart(cart);
                cartItemService.persist(cartItem);
            }
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletePositionFromCart")
    public ResponseEntity<Void> deletePositionFromCart(@RequestBody CartItemDto cartItemDto, Principal principal) {
        if (principal != null) {
            CartItem cartItem = cartItemMapper.dtoToCartItem(cartItemDto);
            cartItem.setShop(shopService.getShop(shopMapper.dtoToShop(cartItemDto.getShopDto()).getName()));
            cartItem.setUser(userService.findByUsername(principal.getName()));
            if (cartItemService.existByUserIdAndItemId(cartItem.getUser().getId(), cartItem.getItem().getId())) {
                CartItem tmpCartItem = cartItemService.getByUserIdAndItemId(cartItem.getUser().getId(), cartItem.getItem().getId());
                List<CartItem> cart = tmpCartItem.getUser().getCart();
                cart.remove(tmpCartItem);
                tmpCartItem.getUser().setCart(cart);
                cartItemService.delete(tmpCartItem);
            }
        }
        return ResponseEntity.ok().build();
    }
}
