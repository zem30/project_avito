package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ReadWriteService;
import com.amr.project.service.impl.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class ShopController {

    private final ShopService shopService1;

    private final ReadWriteService<Shop,Long> shopService;

    public ShopController(ReadWriteService<Shop, Long> shopService, ShopService shopService1) {
        this.shopService = shopService;
        this.shopService1 = shopService1;
    }

    @GetMapping(value = "/shops")
    public String index(Model model) {
        model.addAttribute("shopList", shopService.getAll());
        return "/listShop";
    }

    @GetMapping(value = "/shoppage{id}")
    public String shopPage(Model model,@PathVariable("id") Long id) {
        model.addAttribute("listItems", Objects.requireNonNull(shopService.getAll().stream().filter(shop -> shop.getId().equals(id)).findFirst().orElse(null)).getItems());
        model.addAttribute("shop", shopService.getAll().stream().filter(shop -> shop.getId().equals(id)).findFirst().orElse(null));
        return "/shopPage";
    }
    @GetMapping(value = "/shoppage{id}/items")
    public String itemsList(Model model,@PathVariable("id") Long id) {
        model.addAttribute("item", Objects.requireNonNull(shopService.getAll().stream().filter(shop -> shop.getId().equals(id)).findFirst().orElse(null)).getItems());
        return "/listItems";
    }

}
