package com.amr.project.webapp.rest_controller;


import com.amr.project.converter.DiscountMapper;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.DiscountService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class DiscountRestController {

    private final DiscountService discountService;
    private final DiscountMapper discountMapper;
    private final UserService userService;
    private final ShopService shopService;

    @Autowired
    public DiscountRestController(DiscountService discountService, DiscountMapper discountMapper, UserService userService, ShopService shopService) {
        this.discountService = discountService;
        this.discountMapper = discountMapper;
        this.userService = userService;
        this.shopService = shopService;
    }

    @GetMapping(value = "/discounts")
    public ResponseEntity<List<DiscountDto>> getDiscount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());
        List<DiscountDto> discounts = discountService.findByUser(user);
        return ResponseEntity.ok().body(discounts);
    }

    @GetMapping(value = "/discounts/{shopId}")
    public ResponseEntity<DiscountDto> getDiscountByShopId(@PathVariable("shopId") Long shopId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = Optional.ofNullable(userService.findByUsername(authentication.getName()));
        if (authentication.isAuthenticated() && userOptional.isPresent()) {
            return ResponseEntity.ok(discountService.findByUserAndShop(userOptional.get().getId(), shopId));
        }
        return ResponseEntity.notFound().build();
    }


}