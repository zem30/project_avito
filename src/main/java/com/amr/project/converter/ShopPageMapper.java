package com.amr.project.converter;

import com.amr.project.model.dto.ShopPageDto;
import com.amr.project.model.entity.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopPageMapper {

    ShopPageDto shopToShopDTO(Shop shop);
}
