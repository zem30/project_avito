package com.amr.project.webapp.rest_controller.moderator_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moderator/api/items")
public class ItemModeratorController {
    private final ItemService itemService;
    private final ShopService shopService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemModeratorController(ItemService itemService, ShopService shopService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.shopService = shopService;
        this.itemMapper = itemMapper;
    }

    @GetMapping("/getUnmoderatedItems")
    public ResponseEntity<List<ItemDto>> getUnmoderatedItems() {
        return new ResponseEntity<>(
                itemService.getUnmoderatedItems()
                        .stream()
                        .map(itemMapper::itemToDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/getOneUnmoderatedItem/{id}")
    public ResponseEntity<ItemDto> getOneUnmoderatedItem(@PathVariable("id") Long id) {
        return itemService.getByKey(id).isModerated() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(itemMapper.itemToDto(itemService.getByKey(id)), HttpStatus.OK);
    }

    @PatchMapping("/editItem")
    public ResponseEntity<ItemDto> editItem(@RequestBody ItemDto itemDto) {

        Item item = itemMapper.dtoToItem(itemDto);
        item.setShop(shopService.getByKey(itemDto.getShopId()));
        itemService.update(item);
        return new ResponseEntity<>(itemMapper.itemToDto(itemService.getByKey(itemDto.getId())), HttpStatus.OK);
    }

    @GetMapping("/getUnmoderatedItemsCount")
    public ResponseEntity<Long> getUnmoderatedItemsCount() {
        return new ResponseEntity<>((long) itemService.getUnmoderatedItems().size(), HttpStatus.OK);
    }
}