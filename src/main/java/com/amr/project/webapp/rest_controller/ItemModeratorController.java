package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemModeratorService;
import com.amr.project.service.abstracts.ShopModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moderator/api/items")
public class ItemModeratorController {
    private final ItemModeratorService itemModeratorService;
    private final ShopModeratorService shopModeratorService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemModeratorController(ItemModeratorService itemModeratorService, ShopModeratorService shopModeratorService, ItemMapper itemMapper) {
        this.itemModeratorService = itemModeratorService;
        this.shopModeratorService = shopModeratorService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/getUnmoderatedItems")
    public ResponseEntity<List<ItemDto>> getUnmoderatedItems() {
        return new ResponseEntity<>(
                itemModeratorService.getUnmoderatedItems()
                        .stream()
                        .map(itemMapper::itemToDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }


    @GetMapping("/getOneUnmoderatedItem/{id}")
    public ResponseEntity<ItemDto> getOneUnmoderatedItem(@PathVariable("id") Long id) {
        return itemModeratorService.getByKey(id).isModerated() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(itemMapper.itemToDto(itemModeratorService.getByKey(id)), HttpStatus.OK);
    }

    @PatchMapping("/editItem")
    public ResponseEntity<ItemDto> editItem(@RequestBody ItemDto itemDto) {

        Item item = itemMapper.dtoToItem(itemDto);
        item.setShop(shopModeratorService.getByKey(itemDto.getShopId()));
        itemModeratorService.update(item);
        return new ResponseEntity<>(itemMapper.itemToDto(itemModeratorService.getByKey(itemDto.getId())), HttpStatus.OK);
    }

    @GetMapping("/getUnmoderatedItemsCount")
    public ResponseEntity<Long> getUnmoderatedItemsCount() {
        return new ResponseEntity<>((long) itemModeratorService.getUnmoderatedItems().size(), HttpStatus.OK);
    }
}