package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Shop;
import java.util.List;

public interface ShopModeratorService extends ReadWriteService<Shop, Long> {
    List<Shop> getUnmoderatedShops();
    List<Shop> getModeratedShops();
}
