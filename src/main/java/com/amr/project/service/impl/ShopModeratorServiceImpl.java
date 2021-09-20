package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ShopModeratorDao;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopModeratorServiceImpl extends ReadWriteServiceImpl<Shop, Long> implements ShopModeratorService {
    private ShopModeratorDao shopModeratorDao;
    @Autowired
    protected ShopModeratorServiceImpl(ShopModeratorDao shopModeratorDao) {
        super(shopModeratorDao);
        this.shopModeratorDao = shopModeratorDao;
    }

    @Override
    public List<Shop> getUnmoderatedShops() {
        return shopModeratorDao.getUnmoderatedShops();
    }

    @Override
    public List<Shop> getModeratedShops() {
        return shopModeratorDao.getModeratedShops();
    }
}
