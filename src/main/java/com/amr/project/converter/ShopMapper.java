package com.amr.project.converter;

import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ShopMapper {

    @Mappings({
            @Mapping(target="shopId", source="shop.id"),
            @Mapping(target="shopName", source="shop.name"),
    })
    ShopDto shopToShopDTO(Shop shop);
}
