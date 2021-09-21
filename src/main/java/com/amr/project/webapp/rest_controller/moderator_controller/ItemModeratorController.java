package com.amr.project.webapp.rest_controller.moderator_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moderator/api/items")
@Api(tags = {"API для работы с товарами на странице модератора"})
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

    @ApiOperation(value = "Отправляет все товары не прошедшие модерацию на фронт")
    @GetMapping("/getUnmoderatedItems")
    public ResponseEntity<List<ItemDto>> getUnmoderatedItems() {
        return ResponseEntity.ok(
                itemService.getUnmoderatedItems()
                        .stream()
                        .map(itemMapper::itemToDto)
                        .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Отправляет один не прошедший модерацию товар на фронт по id")
    @GetMapping("/getOneUnmoderatedItem/{id}")
    public ResponseEntity<ItemDto> getOneUnmoderatedItem(@PathVariable("id") Long id) {
        return itemService.getByKey(id).isModerated() ?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(itemMapper.itemToDto(itemService.getByKey(id)));
    }
    @ApiOperation(value = "Получает измененный товар из фронта и обновляет в базе данных")
    @PatchMapping("/editItem")
    public ResponseEntity<ItemDto> editItem(@RequestBody ItemDto itemDto) {

        Item item = itemMapper.dtoToItem(itemDto);
        item.setShop(shopService.getByKey(itemDto.getShopId()));
        itemService.update(item);
        return ResponseEntity.ok(itemMapper.itemToDto(itemService.getByKey(itemDto.getId())));
    }
    @ApiOperation(value = "возвращает на фронт количество не прошедних модерацию товаров для счетчика")
    @GetMapping("/getUnmoderatedItemsCount")
    public ResponseEntity<Long> getUnmoderatedItemsCount() {
        return ResponseEntity.ok((long) itemService.getUnmoderatedItems().size());
    }
}
