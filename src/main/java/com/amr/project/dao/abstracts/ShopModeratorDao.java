package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopModeratorDao extends  ReadWriteDao<Shop, Long> {
    List<Shop> getUnmoderatedShops();
    public List<Shop> getModeratedShops();
}
