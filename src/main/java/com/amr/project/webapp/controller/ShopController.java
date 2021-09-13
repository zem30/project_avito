package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.impl.ReadWriteService;
import com.amr.project.service.impl.ShopServiceImpl;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/api")
@Api(value = "ShopApi", description = "Операции с магазином (получение списка товара, получение магазина по ID)")
@Validated
public class ShopController {

    private final ReadWriteService<Shop,Long> readWriteService;
    private final ShopServiceImpl shopServiceImpl;

    public ShopController(ReadWriteService<Shop, Long> readWriteService, ShopServiceImpl shopServiceImpl) {
        this.readWriteService = readWriteService;
        this.shopServiceImpl = shopServiceImpl;
    }

    @SneakyThrows
    @GetMapping(value = "/shop/{id}")
    public String shopPage(Model model,@PathVariable("id") Long id) {
        Shop shop = readWriteService.getByKey(id);
        Item ratingItem = shopServiceImpl.getTheMostRatingItem(shop.getItems());

        model.addAttribute("shop", shop);
        model.addAttribute("images", shopServiceImpl.convertListImages(ratingItem.getImages()));
        model.addAttribute("logo", shopServiceImpl.convertImage(shop.getLogo()));
        model.addAttribute("ratingItem", ratingItem);
        return "shopPage/shop_page";
    }

    @GetMapping(value = "/shop/{id}/item/{itemId}")
    public String itemPage(Model model,@PathVariable("id") Long id,@PathVariable("itemId") Long itemId) {
        Item item = readWriteService.getByKey(id)
                .getItems()
                .stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null);
        model.addAttribute("item", item);
        model.addAttribute("image", shopServiceImpl.convertListImages(item.getImages()));
        return "shopPage/shop_item_page";
    }

}
