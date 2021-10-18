package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ShopService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop_api")
@AllArgsConstructor
public class ShopRestController {


    private final ShopService shopService;
    private final ShopMapper shopConverter;

    @GetMapping("/{name}")
    public ResponseEntity<ShopDto> getShop(@PathVariable @NonNull String name) {
        Shop shop = shopService.getShop(name);
        return ResponseEntity.ok().body(shopConverter.shopToDto(shop));
    }

    //8888
    @GetMapping("/shops")
    public List<ShopDto> getAllShop(){
        List<ShopDto> shopDtoList = shopService.getAllShopsRatingSort();
        return shopDtoList;
    }
    //8888
    @GetMapping("/shop/{id}")
    public ShopDto shop(@PathVariable("id") long id){
        ShopDto shopDto = shopService.getShopId(id);
        return shopDto;
    }
}
