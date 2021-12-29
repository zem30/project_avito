package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.AdvertMapper;
import com.amr.project.model.dto.AdvertDto;
import com.amr.project.model.entity.Advert;
import com.amr.project.service.abstracts.AdvertService;
import com.amr.project.service.abstracts.CategoryService;
import com.amr.project.service.abstracts.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adverts")
@AllArgsConstructor
@Validated
public class AdvertRestController {

    private final AdvertService advertService;
    private final AdvertMapper advertMapper;
    private final CategoryService categoryService;
    private final UserService userService;


    @ApiOperation(value = "Добавление объявления пользователя")
    @PostMapping()
    public ResponseEntity<?> addAdvert(@Valid @RequestBody AdvertDto advertDto, Principal principal) {
        Advert advert = advertMapper.dtoToAdvert(advertDto);
        if (advertDto.getName() == null) {
            return ResponseEntity.badRequest().body("empty advert");
        }
        advert.setCategories(Arrays.stream(advertDto.getCategoriesName())
                .map(categoryService::getCategory)
                .collect(Collectors.toList()));
        advert.setUser(userService.findByUsername(principal.getName()));
        advertService.persist(advert);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Обновляет объявления пользователя")
    @PutMapping()
    public ResponseEntity<AdvertDto> updateItem(@RequestBody @NonNull AdvertDto advertDto) {
        Advert advert = advertMapper.dtoToAdvert(advertDto);
        if (advertDto.getName() == null || advertDto.getCategories() == null || advertDto.getCategoriesName() == null)
            return ResponseEntity.badRequest().build();
        advert.setId(advertDto.getId());
        advert.setUser(userService.getUserId(advertDto.getId()));
        advert.setCategories(Arrays.stream(advertDto.getCategoriesName())
                .map(categoryService::getCategory)
                .collect(Collectors.toList()));
        advertService.update(advert);
        return ResponseEntity.ok().body(advertMapper.advertToDto(advert));

    }

    @GetMapping("{id}")
    public ResponseEntity<AdvertDto> getAdvert(@PathVariable @NonNull Long id) {
        Advert advert = advertService.getByKey(id);
        return ResponseEntity.ok().body(advertMapper.advertToDto(advert));
    }

    @GetMapping()
    public List<AdvertDto> getAllAdverts() {
        return advertService.getAllAdverts();
    }

    @ApiOperation(value = "Список объявлений пользователя")
    @GetMapping("user/{id}")
    public List<AdvertDto> getAllAdvertsByUser(@PathVariable @NonNull Long id) {
        return advertService.getAllUser(userService.getUserId(id))
                .stream()
                .map(advertMapper::advertToDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Удаляет объект Advert по id")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteItem(@PathVariable @NonNull Long id) {
        Advert advert = advertService.getByKey(id);
        if (advert == null)
            return ResponseEntity.badRequest().body("empty advert");
        advert.setPretendentToBeDeleted(true);
        advertService.update(advert);
        return ResponseEntity.ok().build();
    }

}
