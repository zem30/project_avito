package com.amr.project.converter;

import com.amr.project.model.dto.ShopPageImageDto;
import com.amr.project.model.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopPageImageMapper {

    ShopPageImageDto imageConvertToShopPageDto(Image image);

}
