package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ReadWriteService;
import com.amr.project.service.impl.ShopService;
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
    private final ShopService shopService;

    public ShopController(ReadWriteService<Shop, Long> readWriteService, ShopService shopService1) {
        this.readWriteService = readWriteService;
        this.shopService = shopService1;
    }

    @SneakyThrows
    @GetMapping(value = "/shop/{id}")
    public String shopPage(Model model,@PathVariable("id") Long id) {
        Shop shop = readWriteService.getByKey(id);
        Item ratingItem = shopService.getTheMostRatingItem(shop.getItems());

        model.addAttribute("shop", shop);
        model.addAttribute("images", shopService.convertListImages(ratingItem.getImages()));
        model.addAttribute("logo", shopService.convertImage(shop.getLogo()));
        model.addAttribute("ratingItem", ratingItem);
        return "shop_page";
    }

    @GetMapping(value = "/shop/{id}/list")
    public String shopItems(Model model,@PathVariable("id") Long id) {
        Shop shop = readWriteService.getByKey(id);
        List<Item> itemList = readWriteService.getByKey(id).getItems();
        model.addAttribute("itemList", itemList);
        model.addAttribute("shop", shop);
        model.addAttribute("logo", shopService.convertImage(shop.getLogo()));
        return "shop_items_list";
    }

    @GetMapping(value = "/shop/{id}/item/{itemId}")
    public String itemPage(Model model,@PathVariable("id") Long id,@PathVariable("itemId") Long itemId) {
        Item item = readWriteService.getByKey(id)
                .getItems()
                .stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst().orElse(null);
        model.addAttribute("item", item);
        model.addAttribute("image", shopService.convertListImages(item.getImages()));
        return "shop_item_page";
    }
    @GetMapping(value = "/shops")
    public String listShops(Model model) {
        List<Shop> shopList = shopService.getAll();
        Map<Shop,String> map = new LinkedHashMap<>();
        for (Shop shop : shopList) {
            map.put(shop, shopService.convertImage(shop.getLogo()));
        }
        model.addAttribute("map",map);
        return "shop_list";
    }
}

//    @ApiOperation(value = "Поиск магазина по ID")
//    @GetMapping(path = "/{id}")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Магазин не найден по id"),
//            @ApiResponse(code = 404, message = "Магазин не найден по id")
//    })
//    public ResponseEntity<?> findUser(@PathVariable Long id) {
//        if (readWriteService.existsById(id)) {
//            return ResponseEntity.ok(readWriteService.getByKey(id));
//        }
//        logger.info(String.format("Магазин с указанным ID: %d не найден!", id));
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Shop with ID: %d does not exist", id));
//
//    }
