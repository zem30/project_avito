package com.amr.project.webapp.rest_controller.shop_owner_controller;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.impl.DiscountServiceImpl;
import com.amr.project.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"API для получения пользователей и добавления скидки на странице владельца магазина"})
@RestController
@RequestMapping()
@Validated
@RequiredArgsConstructor
public class ShopOwnerRestController {

    private final UserServiceImpl userService;
    private final ShopService shopService;
    private final DiscountServiceImpl discountService;
    private final UserMapper userMapper;
    private final DiscountMapper discountMapper;
    private final ShopMapper shopMapper;


    @ApiOperation(value = "Получаем всех пользователей с ролью \"Пользователь\" ")
    @GetMapping("/userlist/all")
    public ResponseEntity<List<UserDto>> findAllUsersWithRoleUser() {
        return ResponseEntity.ok(userService.findByRole("User")
                .stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Получаем пользователя по ID ")
    @GetMapping("/userlist/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        User user = userService.getByKey(id);
        return new ResponseEntity<>(userMapper.userToDto(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Получаем магазин по ID ")
    @GetMapping("/userlist/shop/{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable("id") long id) {
        Shop shop = shopService.getByKey(id);
        return new ResponseEntity<>(shopMapper.shopToDto(shop), HttpStatus.OK);
    }

    @ApiOperation(value = "Добавление скидки")
    @PutMapping("/userlist/addDiscount")
    public ResponseEntity<Discount> addDiscount(@Valid @RequestBody DiscountDto discountDto) {
        Discount discount = discountMapper.discountDtoToDiscount(discountDto);
        discountService.persist(discount);
        return new ResponseEntity<>(discount,HttpStatus.OK);
    }

}
