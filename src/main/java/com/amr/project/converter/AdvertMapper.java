package com.amr.project.converter;

import com.amr.project.model.dto.AdvertDto;
import com.amr.project.model.entity.Advert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {UserMapper.class, ImageMapper.class})
public interface AdvertMapper {
    @Mapping(target = "userId" , source ="user.id")
    AdvertDto advertToDto(Advert advert);

    @Mapping(target = "user.id" , source ="userId")
    Advert dtoToAdvert(AdvertDto advertDto);
}
