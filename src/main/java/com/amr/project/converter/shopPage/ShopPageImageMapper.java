package com.amr.project.converter.shopPage;

import com.amr.project.model.dto.shopPage.ShopPageImageDto;
import com.amr.project.model.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopPageImageMapper {

    ShopPageImageDto imageConvertToShopPageDto(Image image);

}
