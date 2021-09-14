package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopPageImageMapper;
import com.amr.project.converter.ShopPageItemMapper;
import com.amr.project.converter.ShopPageMapper;
import com.amr.project.model.dto.ShopPageDto;
import com.amr.project.model.dto.ShopPageItemDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ReadWriteService;
import com.amr.project.service.impl.ShopServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/api")
@Api(value = "ShopApi", description = "Операции с магазином (получение списка товара, получение магазина по ID)")
@RequiredArgsConstructor
@Validated
public class ShopController {

    private final ReadWriteService<Shop,Long> readWriteService;
    private final ShopServiceImpl shopServiceImpl;

    private final ShopPageMapper shopPageMapper;
    private final ShopPageImageMapper shopPageImageMapper;
    private final ShopPageItemMapper shopPageItemMapper;



    @GetMapping(value = "/shop/{id}")
    public String shopPage(Model model,@PathVariable("id") Long id) {

        ShopPageDto shop =shopPageMapper.shopToShopDTO(readWriteService.getByKey(id));
        ShopPageItemDto ratingItem = shopServiceImpl.getTheMostRatingItem(shop.getItems());

        List<String> images = shopServiceImpl.convertListImages(ratingItem.getImages());
        String logo = shopServiceImpl.convertImage(shopPageImageMapper.imageConvertToShopPageDto(shop.getLogo()));

        model.addAttribute("shop", shop);
        model.addAttribute("images", images);
        model.addAttribute("logo", logo);
        model.addAttribute("ratingItem", ratingItem);

        return "shopPage/shop_page";
    }

    @GetMapping(value = "/shop/{id}/item/{itemId}")
    public String itemPage(Model model,@PathVariable("id") Long shopId,@PathVariable("itemId") Long itemId) {

        ShopPageItemDto item =shopPageItemMapper.itemConvertToShopPageItemDto(
                shopServiceImpl.getItemById(readWriteService.getByKey(shopId).getItems(),itemId));
        List<String> image = shopServiceImpl.convertListImages(item.getImages());

        model.addAttribute("item",item);
        model.addAttribute("image", image);
        return "shopPage/shop_item_page";
    }

}
