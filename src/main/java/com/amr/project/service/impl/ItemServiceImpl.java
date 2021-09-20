package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReadWriteDao;
import com.amr.project.dao.impl.ItemDaoImpl;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ItemService {

    private ItemDaoImpl itemDao;

    public ItemServiceImpl(ReadWriteDao<Item, Long> dao, ItemDaoImpl itemDao) {
        super(dao);
        this.itemDao = itemDao;
    }

    @Override
    @Transactional
    public Item getItemName(String nameItem) {
        return itemDao.getItemName(nameItem);
    }


}
