package com.amr.project.converter;

import com.amr.project.model.dto.shopPage.ItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto itemConvertToItemDto(Item item);
}
