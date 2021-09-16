package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ItemModeratorDao;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemModeratorServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemModeratorService {

    ItemModeratorDao itemModeratorDao;
    @Autowired
    public ItemModeratorServiceImpl(ItemModeratorDao itemModeratorDao) {
        super(itemModeratorDao);
        this.itemModeratorDao = itemModeratorDao;
    }

    @Override
    public List<Item> getUnmoderatedItems() {
        return itemModeratorDao.getUnmoderatedItems();
    }

    @Override
    public List<Item> getModeratedItems() {
        return itemModeratorDao.getModeratedItems();
    }
}