package com.amr.project.dao.util;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ReadWriteService;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class FillShopTable {

    private final ReadWriteService<Shop,Long> shopService;

    public FillShopTable(ReadWriteService<Shop, Long> shopService) {
        this.shopService = shopService;
    }

    @PostConstruct
    void creatAndFillShop(){
        Shop shop1 = new Shop();
        shop1.setName("ShopName1");
        List<Item> itemList = new ArrayList<>();
        Item item = new Item();
        item.setName("Товар");
        itemList.add(item);
        shopService.persist(shop1);
        shopService.getAll().forEach(System.out::println);
    }
}
