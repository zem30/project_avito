package com.amr.project.webapp.rest_controller.moderator_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.converter.ReviewMapper;
import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("moderator/api/shops")
public class ShopModeratorController {
    private final ShopService shopService;
    private final ItemService itemService;
    private final ShopMapper shopMapper;
    private final ItemMapper itemMapper;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ShopModeratorController(ShopService shopService, ShopMapper shopMapper, ItemService itemService, ItemMapper itemMapper, ReviewMapper reviewMapper) {
        this.shopService = shopService;
        this.shopMapper = shopMapper;
        this.itemService = itemService;
        this.itemMapper = itemMapper;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/getUnmoderatedShops")
    public ResponseEntity<List<ShopDto>> getUnmoderatedShops() {
        return new ResponseEntity<>(
                shopService
                        .getUnmoderatedShops()
                        .stream().map(shopMapper::shopToDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping("/getOneUnmoderatedShop/{id}")
    public ResponseEntity<ShopDto> getOneUnmoderatedItem(@PathVariable("id") Long id) {
        return shopService.getByKey(id).isModerated() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(shopMapper.shopToDto(shopService.getByKey(id)), HttpStatus.OK);
    }

    @PutMapping("/editShop")
    public ResponseEntity<ShopDto> editItem(@RequestBody ShopDto shopDto) {
        Shop shop = shopMapper.dtoToShop(shopDto);
        shopService.update(shop);
        return new ResponseEntity<>(shopMapper.shopToDto(shop), HttpStatus.OK);
    }

    @GetMapping("/getUnmoderatedShopsCount")
    public ResponseEntity<Long> getUnmoderatedItemsCount() {
        return new ResponseEntity<>((long) shopService
                .getUnmoderatedShops()
                .size(),
                HttpStatus.OK);
    }
}



