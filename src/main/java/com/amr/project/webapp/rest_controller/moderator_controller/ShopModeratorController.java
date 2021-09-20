package com.amr.project.webapp.rest_controller.moderator_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.converter.ReviewMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ItemModeratorService;
import com.amr.project.service.abstracts.ShopModeratorService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("moderator/api/shops")
public class ShopModeratorController {
    private final ShopModeratorService shopModeratorService;
    private final ItemModeratorService itemModeratorService;
    private final ReviewModeratorController reviewModeratorController;
    private final ShopMapper shopMapper;
    private final ItemMapper itemMapper;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ShopModeratorController(ShopModeratorService shopModeratorService, ShopMapper shopMapper, UserService userService, ItemModeratorService itemModeratorService, ReviewModeratorController reviewModeratorController, ItemMapper itemMapper, ReviewMapper reviewMapper) {
        this.shopModeratorService = shopModeratorService;
        this.shopMapper = shopMapper;
        this.itemModeratorService = itemModeratorService;
        this.reviewModeratorController = reviewModeratorController;
        this.itemMapper = itemMapper;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/getUnmoderatedShops")
    public ResponseEntity<List<ShopDto>> getUnmoderatedShops() {
//        List<ShopDto> list = shopModeratorService.getUnmoderatedShops().stream().map(shopMapper::shopToDto).collect(Collectors.toList());
//        System.out.println(list);
        return new ResponseEntity<>(
                shopModeratorService
                        .getUnmoderatedShops()
                        .stream().map(shopMapper::shopToDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping("/getOneUnmoderatedShop/{id}")
    public ResponseEntity<ShopDto> getOneUnmoderatedItem(@PathVariable("id") Long id) {
        return shopModeratorService.getByKey(id).isModerated() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(shopMapper.shopToDto(shopModeratorService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/editShop")
    public ResponseEntity<ShopDto> editItem(@RequestBody ShopDto shopDto) {
        Shop shop = shopMapper.dtoToShop(shopDto);
        shopModeratorService.update(shop);
        return new ResponseEntity<>(shopMapper.shopToDto(shop), HttpStatus.OK);
    }

    @GetMapping("/getUnmoderatedShopsCount")
    public ResponseEntity<Long> getUnmoderatedItemsCount() {
        return new ResponseEntity<>((long) shopModeratorService
                .getUnmoderatedShops()
                .size(),
                HttpStatus.OK);
    }
}



