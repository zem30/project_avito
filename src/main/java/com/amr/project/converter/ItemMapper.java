package com.amr.project.converter;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto itemConvertToShopPageItemDto(Item item);

    Item ItemDtoToItem(ItemDto itemDto);

    ItemDto ItemToItemDto(Item item);

}
