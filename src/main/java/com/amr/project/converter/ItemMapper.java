package com.amr.project.converter;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ImageMapper.class, CategoryMapper.class,
        ReviewMapper.class})
public interface ItemMapper {

    @Mapping(source = "shop.id", target = "shopId")
    ItemDto itemToDto(Item item);

    ItemDto itemConvertToShopPageItemDto(Item item);

    Item ItemDtoToItem(ItemDto itemDto);

    ItemDto ItemToItemDto(Item item);

    @Mapping(source = "shopId", target = "shop.id")
    Item dtoToItem(ItemDto itemDto);
}
