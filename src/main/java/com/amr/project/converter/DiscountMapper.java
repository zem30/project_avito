package com.amr.project.converter;

import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiscountMapper {

    @Mapping(source = "shop.id", target = "shopId")
    @Mapping(source = "user.id", target = "userId")
    DiscountDto discountToDiscountDto(Discount discount);

    @Mapping(source = "shopId", target = "shop.id")
    @Mapping(source = "userId", target = "user.id")
    Discount DiscountDtoToDiscount (DiscountDto discountDto);



}
