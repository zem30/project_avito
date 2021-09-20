package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Shop;

public interface ShopDao extends ReadWriteDao<Shop, Long> {
    Shop getShop(String nameShop);
}
