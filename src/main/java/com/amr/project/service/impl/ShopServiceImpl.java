package com.amr.project.service.impl;

import com.amr.project.dao.impl.ReadWriteDao;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
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

    public Item getTheMostRatingItem(List<Item> itemList) {
        return itemList.stream().max(Comparator.comparingDouble(Item::getRating)).orElseGet(null);
    }

    public String convertImage(Image image) {
        return Base64.encode(image.getPicture());
    }

    public List<String> convertListImages(List<Image> list) {
        return list.stream().map(s -> Base64.encode(s.getPicture())).collect(Collectors.toList());

    }
}
