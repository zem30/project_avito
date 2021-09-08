package com.amr.project.service.impl;

import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ReadWriteService;
import com.amr.project.service.abstracts.ReadWriteServiceAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.scribejava.core.base64.Base64;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService extends ReadWriteServiceAbstract<Shop, Long> {

    public Item getTheMostRatingItem(List<Item> itemList) {
        return itemList.stream().max(Comparator.comparingDouble(Item::getRating)).get();
    }

    public String convertImage(Image image) {
        return Base64.encode(image.getPicture());
    }

    public List<String> convertListImages(List<Image> list) {
        return list.stream().map(s -> Base64.encode(s.getPicture())).collect(Collectors.toList());

    }
}
