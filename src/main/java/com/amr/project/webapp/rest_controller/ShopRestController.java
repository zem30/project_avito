package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop_api")
@AllArgsConstructor
@Validated
public class ShopRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ShopMapper shopMapper;
    private final UserService userService;
    private final ShopService shopService;
    private final ShopMapper shopConverter;

    @GetMapping("/{name}")
    public ResponseEntity<ShopDto> getShop(@PathVariable @NonNull String name) {
        Shop shop = shopService.getShop(name);
        return ResponseEntity.ok().body(shopConverter.shopToDto(shop));
    }

    @PostMapping("/")
    @ApiOperation(value = "Добавляет новый магазин")
    public  ResponseEntity<ShopDto> addShop(@Valid @RequestBody ShopDto shopDto, Errors bindingResult){
        System.out.println(bindingResult.hasErrors());
        Shop shop = shopMapper.dtoToShop(shopDto);
        shop.setUser(userService.getAuthorized());
        shopService.persist(shop);
        LOGGER.info("Магазин с id: " + shop.getId() + " успешно добавлен");
        return ResponseEntity.ok().build();
    }

    //8888
    @GetMapping("/shops")
    public List<ShopDto> getAllShop() {
        List<ShopDto> shopDtoList = shopService.getAllShopsRatingSort();
        return shopDtoList;
    }

    //8888
    @GetMapping("/shop/{id}")
    public ShopDto shop(@PathVariable("id") long id) {
        ShopDto shopDto = shopService.getShopId(id);
        return shopDto;
    }

}
