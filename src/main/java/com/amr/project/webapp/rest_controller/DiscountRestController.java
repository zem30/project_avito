package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.converter.UserMapper;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Discount;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.model.enums.Status;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.impl.DiscountServiceImpl;
import com.amr.project.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"API для получения пользователей и добавления скидки на странице владельца магазина"})
@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class DiscountRestController {

    private final UserServiceImpl userService;
    private final ShopService shopService;
    private final DiscountServiceImpl discountService;
    private final UserMapper userMapper;
    private final DiscountMapper discountMapper;
    private final ShopMapper shopMapper;

    @ApiOperation(value = "Получение списка скидок по id магазина")
    @GetMapping("shop/{id}/discounts")
    public ResponseEntity<List<DiscountDto>> getAllDiscountByShipId(@PathVariable("id") long id) {
        return ResponseEntity.ok(discountService.findByShop(shopMapper.dtoToShop(shopService.getShopId(id))));
    }

    @ApiOperation(value = "Создание скидки")
    @PostMapping("discounts")
    public ResponseEntity<DiscountDto> createDiscount(@RequestBody DiscountDto discountDto) {
        if ((discountDto.getMinOrder() == null || discountDto.getShopId() == null) &&
                (discountDto.getPercentage() == null || discountDto.getFixedDiscount() == null)) {
            return ResponseEntity.badRequest().build();
        }
        Discount discount = discountMapper.discountDtoToDiscount(discountDto);
        discount.setShop(shopMapper.dtoToShop(shopService.getShopId(discountDto.getShopId())));
        discount = discountService.saveD(discount);
        return ResponseEntity.ok().body(discountMapper.discountToDiscountDto(discount));
    }

    @ApiOperation(value = "Добавление скидок пользователям")
    @PostMapping("discounts/users")
    public ResponseEntity<?> applyDiscount(@RequestBody DiscountDto discountDto) {
        if ((discountDto.getMinOrder() == null || discountDto.getShopId() == null) &&
                (discountDto.getPercentage() == null || discountDto.getFixedDiscount() == null)) {
            return ResponseEntity.badRequest().build();
        }
        Discount discount = discountService.findByAllFields(discountDto.getMinOrder(), discountDto.getPercentage(),
                discountDto.getFixedDiscount(),
                shopMapper.dtoToShop(shopService.getShopId(discountDto.getShopId()))).orElse(null);

        List<User> users = discountDto.getUsers().stream().map(userMapper::dtoToUser).collect(Collectors.toList());

        users.forEach(u -> {
            User user = userService.findByUsername(u.getUsername());
            List<Discount> discounts = user.getDiscounts().stream()
                    .filter(d -> !d.getShop().equals(discount.getShop()))
                    .collect(Collectors.toList());
            discounts.add(discount);
            user.setDiscounts(discounts);
            userService.update(user);
        });
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Список всех клиентов магазина")
    @GetMapping("shop/{id}/buyers")
    public ResponseEntity<List<UserDto>> findAllBuyersForShop(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findAllBuyersForShop(
                        shopMapper.dtoToShop(shopService.getShopId(id)))
                .stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList()));
    }


    @ApiOperation(value = "Получаем всех пользователей с ролью \"Пользователь\"")
    @GetMapping("/userlist/all")
    public ResponseEntity<List<UserDto>> findAllUsersWithRoleUser() {
        return ResponseEntity.ok(userService.findByRole("User")
                .stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Получаем всех пользователей с наличием хотя бы одной покупки")
    @GetMapping("/userlist/allbuyers")
    public ResponseEntity<List<UserDto>> findAllBuyers() {
        return ResponseEntity.ok(userService.findByStatusOrder(Status.COMPLETE)
                .stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Получаем всех пользователей с наличием хотя бы одной покупки " +
            "в магазинах пользователя c User.id = {id}")
    @GetMapping("/userlist/{id}/allbuyers")
    public ResponseEntity<List<UserDto>> findByStatusOrderAndShopOwnerUser(@PathVariable("id") long id) {
        User user = userService.getUserId(id);
        return ResponseEntity.ok(userService.findByStatusOrderAndShopOwnerUser(Status.COMPLETE, user)
                .stream()
                .map(userMapper::userToDto)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Получаем пользователя по ID ")
    @GetMapping("/userlist/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        User user = userService.getByKey(id);
        return ResponseEntity.ok().body(userMapper.userToDto(user));

    }

    @ApiOperation(value = "Получаем магазин по ID ")
    @GetMapping("/userlist/shop/{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable("id") long id) {
        Shop shop = shopService.getByKey(id);
        return ResponseEntity.ok().body(shopMapper.shopToDto(shop));
    }

    @ApiOperation(value = "Добавление скидки")
    @PostMapping("/userlist/addDiscount")
    public ResponseEntity<?> addDiscount(@RequestBody DiscountDto discountDto) {
//        if (discountDto.getShop() == null | discountDto.getUser() == null)
//            return ResponseEntity.badRequest().build();
        Discount discount = discountMapper.discountDtoToDiscount(discountDto);
//        discount.setShop(shopService.getShop(discountDto.getShop().getName()));
//        discount.setUser(userService.findByUsername(discountDto.getUser().getUsername()));
        discountService.persist(discount);
        return ResponseEntity.ok().build();
    }

}
