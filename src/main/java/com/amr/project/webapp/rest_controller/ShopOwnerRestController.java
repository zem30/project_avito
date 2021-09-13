package com.amr.project.webapp.rest_controller;

import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.User;
import com.amr.project.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
@Validated
public class ShopOwnerRestController {

    private final UserServiceImpl userService;

    @Autowired
    public ShopOwnerRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Discount>> getOneUser(@PathVariable("id") long id) {
        List<Discount> discountList = userService.getByKey(id).getDiscounts();
        return new ResponseEntity<>(discountList, HttpStatus.OK);
    }

    @PostMapping("/addDiscount")
    public ResponseEntity<Discount> addNewUser(@RequestBody Discount discount) {
        User user = userService.getByKey(discount.getUser().getId());
        List<Discount> discountList = user.getDiscounts();
        discountList.add(discount);
        userService.update(user);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

}
