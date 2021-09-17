package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;

public interface ItemDao extends ReadWriteDao<Item, Long> {
    Item getItemName(String nameItem);
}
