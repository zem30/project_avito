package com.amr.project.converter;

import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UserMapper.class,ShopMapper.class})
public interface DiscountMapper {
//    @Mapping(target = "city" , source ="city.name")
//    @Mapping(target = "country" , source ="country.name")
    DiscountDto discountToDiscountDto(Discount discount);
//    @Mapping(target = "city.name" , source ="city")
//    @Mapping(target =  "country.name", source ="country")
    Discount discountDtoToDiscount(DiscountDto discountDto);
}
