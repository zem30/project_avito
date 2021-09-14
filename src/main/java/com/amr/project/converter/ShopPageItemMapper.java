package com.amr.project.converter;

import com.amr.project.model.dto.ShopPageItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopPageItemMapper {

    ShopPageItemDto itemConvertToShopPageItemDto(Item item);
}
