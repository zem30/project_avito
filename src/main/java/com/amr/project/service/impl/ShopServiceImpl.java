package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.abstracts.ShopDao;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import com.github.scribejava.core.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop,Long> implements ShopService {

    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ReadWriteDao<Shop, Long> dao, ShopDao shopDao) {
        super(dao);
        this.shopDao = shopDao;
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

    @Override
    public Shop getShop(String nameShop) {
        return shopDao.getShop(nameShop);
    }
    @Override
    public List<Shop> getUnmoderatedShops() {
        return shopDao.getUnmoderatedShops();
    }

    @Override
    public List<Shop> getModeratedShops() {
        return shopDao.getModeratedShops();
    }
}
