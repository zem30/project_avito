package com.amr.project.webapp.rest_controller.shop_owner_controller;

import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.User;
import com.amr.project.service.impl.DiscountServiceImpl;
import com.amr.project.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/myshop/userlist", produces = "application/json")
@Validated
public class ShopOwnerRestController {

    private final UserServiceImpl userService;
    private final DiscountServiceImpl discountService;

    @Autowired
    public ShopOwnerRestController(UserServiceImpl userService, DiscountServiceImpl discountService) {
        this.userService = userService;
        this.discountService = discountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsersWithRoleUser() {
        return ResponseEntity.ok(userService.findByRole());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getByKey(id), HttpStatus.OK);
    }

    @PutMapping("/addDiscount")
    public ResponseEntity<Discount> update(@Valid @RequestBody Discount discount) {
        User user = userService.getByKey(discount.getId());

        Discount disc = Discount.builder().build();
        disc.setFixedDiscount(discount.getFixedDiscount());
        disc.setPercentage(discount.getPercentage());
        disc.setMinOrder(discount.getMinOrder());
        disc.setShop(discount.getShop());
        disc.setUser(user);

        discountService.persist(disc);
        return new ResponseEntity<>(discount,HttpStatus.OK);
    }

}
