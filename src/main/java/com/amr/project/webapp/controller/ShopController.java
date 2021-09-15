package com.amr.project.webapp.controller;

import com.amr.project.converter.shopPage.ShopPageImageMapper;
import com.amr.project.converter.shopPage.ShopPageItemMapper;
import com.amr.project.converter.shopPage.ShopPageMapper;
import com.amr.project.model.dto.shopPage.ShopPageDto;
import com.amr.project.model.dto.shopPage.ShopPageItemDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.impl.ReadWriteService;
import com.amr.project.service.impl.ShopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
@Controller
@RequestMapping()
@RequiredArgsConstructor
@Validated
public class ShopController {

    private final ReadWriteService<Shop,Long> readWriteService;
    private final ShopServiceImpl shopServiceImpl;

    private final ShopPageMapper shopPageMapper;
    private final ShopPageImageMapper shopPageImageMapper;
    private final ShopPageItemMapper shopPageItemMapper;

    @GetMapping("allShops")
    public ResponseEntity<List<ShopPageDto>> getAllShops() {
        return ResponseEntity.ok(shopServiceImpl.getAll().stream()
                .map(shopPageMapper::shopToShopDTO)
                .collect(Collectors.toList()));
    }


    @GetMapping(value = "/shop{id}")
    public String shopPage(Model model,@PathVariable("id") Long id) {

        ShopPageDto shop =shopPageMapper.shopToShopDTO(readWriteService.getByKey(id));
        ShopPageItemDto ratingItem = shopServiceImpl.getTheMostRatingItem(shop.getItems());
        List<String> images = null;
        try {
             images = shopServiceImpl.convertListImages(ratingItem.getImages());
        }catch (NullPointerException ignore){
            
        }
        String logo = shopServiceImpl.convertImage(shopPageImageMapper.imageConvertToShopPageDto(shop.getLogo()));

        model.addAttribute("shop", shop);
        model.addAttribute("images", images);
        model.addAttribute("logo", logo);
        model.addAttribute("ratingItem", ratingItem);

        return "shopPage/shop_page";
    }

    @GetMapping(value = "/shop{id}/item{itemId}")
    public String itemPage(Model model,@PathVariable("id") Long shopId,@PathVariable("itemId") Long itemId) {

        ShopPageItemDto item =shopPageItemMapper.itemConvertToShopPageItemDto(
                shopServiceImpl.getItemById(readWriteService.getByKey(shopId).getItems(),itemId));

        List<String> image = shopServiceImpl.convertListImages(item.getImages());

        model.addAttribute("item",item);
        model.addAttribute("image", image);
        return "shopPage/shop_item_page";
    }

}
