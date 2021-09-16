package com.amr.project.converter;

import com.amr.project.model.dto.shopPage.ShopDto;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    ShopDto shopToShopDTO(Shop shop);
}
