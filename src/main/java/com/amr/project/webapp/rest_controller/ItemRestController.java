package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ItemMapper;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.impl.CategoryServiceImpl;
import com.amr.project.service.impl.ItemServiceImpl;
import com.amr.project.service.impl.ShopServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Validated
@RestControllerAdvice
@RestController
@RequiredArgsConstructor
@RequestMapping("shop/")
public class ItemRestController {

    private final CategoryServiceImpl categoryService;

    private final ShopServiceImpl shopService;

    private final ItemServiceImpl itemServiceImpl;

    private final ItemMapper itemConverter;

    @PostMapping("item")
    public ResponseEntity<Void> addItem(@RequestBody ItemDto itemDto) {
        Item item = itemConverter.ItemDtoToItem(itemDto);
        item.setCategories(Arrays.stream(itemDto.getCategoriesName()).map(category -> categoryService.getCategory(category)).collect(Collectors.toList()));
        item.setShop(shopService.getShop(itemDto.getShopName()));
        itemServiceImpl.persist(item);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable @NonNull Long id) {
        Item item = itemServiceImpl.getByKey(id);
        item.setPretendentToBeDeleted(true);
        itemServiceImpl.update(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping("item")
    public ResponseEntity<ItemDto> updateItem(@RequestBody @NonNull ItemDto itemDto) {
        Item item = itemConverter.ItemDtoToItem(itemDto);
        item.setId(itemServiceImpl.getItemName(itemDto.getName()).getId());
        item.setCategories(Arrays.stream(itemDto.getCategoriesName()).map(category -> categoryService.getCategory(category)).collect(Collectors.toList()));
        itemServiceImpl.update(item);
        return ResponseEntity.ok().body(itemConverter.ItemToItemDto(item));
    }
}

