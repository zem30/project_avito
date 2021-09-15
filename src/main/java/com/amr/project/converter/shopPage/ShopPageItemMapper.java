package com.amr.project.converter.shopPage;

import com.amr.project.model.dto.shopPage.ShopPageItemDto;
import com.amr.project.model.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopPageItemMapper {

    ShopPageItemDto itemConvertToShopPageItemDto(Item item);
}
