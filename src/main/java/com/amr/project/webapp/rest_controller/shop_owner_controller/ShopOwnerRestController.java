package com.amr.project.webapp.rest_controller.shop_owner_controller;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.User;
import com.amr.project.service.impl.DiscountServiceImpl;
import com.amr.project.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/myshop/userlist", produces = "application/json")
@Validated
@RequiredArgsConstructor
public class ShopOwnerRestController {

    private final UserServiceImpl userService;
    private final DiscountServiceImpl discountService;
    private final UserMapper userMapper;
    private final DiscountMapper discountMapper;


    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAllUsersWithRoleUser() {
        return ResponseEntity.ok(userService.findByRole("User")
                .stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        User user = userService.getByKey(id);
        return new ResponseEntity<>(userMapper.userToDto(user), HttpStatus.OK);
    }


    @PutMapping("/addDiscount")
    public ResponseEntity<Discount> addDiscount(@Valid @RequestBody DiscountDto discountDto) {
        Discount discount = discountMapper.discountDtoToDiscount(discountDto);
        discountService.persist(discount);
        return new ResponseEntity<>(discount,HttpStatus.OK);
    }

}
