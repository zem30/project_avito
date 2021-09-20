package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Item;

import java.util.List;
public interface ItemModeratorService extends ReadWriteService<Item, Long> {

    List<Item> getUnmoderatedItems();
    List<Item> getModeratedItems();
}
