package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {
    private final ItemDao itemDao;

    protected ItemServiceImpl(ReadWriteDao<Item, Long> dao, ItemDao itemDao) {
        super(dao);
        this.itemDao = itemDao;
    }


    @Override
    public List<Item> getAllItem() {
        return itemDao.getAll();
    }
}
