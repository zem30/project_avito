package com.amr.project.service.impl;

import com.amr.project.dao.impl.ReadWriteDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ReadWriteServiceImpl<Shop,Long> {
    protected ShopServiceImpl(ReadWriteDao<Shop, Long> dao) {
        super(dao);
    }
}
