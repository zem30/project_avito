package com.amr.project.service.impl;

import com.amr.project.dao.impl.ReadWriteDao;
import com.amr.project.model.dto.shopPage.ImageDto;
import com.amr.project.model.dto.shopPage.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ReadWriteServiceImpl;
import com.github.scribejava.core.base64.Base64;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop,Long> {

    protected ShopServiceImpl(ReadWriteDao<Shop, Long> dao) {
        super(dao);
    }

    public ItemDto getTheMostRatingItem(List<ItemDto> itemList) {
        return itemList.stream()
                .max(Comparator.comparingDouble(ItemDto::getRating)).get();
    }

    public String convertImage(ImageDto image) {
        return Base64.encode(image.getPicture());
    }

    public List<String> convertListImages(List<ImageDto> list) {
        return list.stream()
                .map(s -> Base64.encode(s.getPicture()))
                .collect(Collectors.toList());

    }

    public Item getItemById (List<Item> itemList,long id) {
        return itemList.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }
}
