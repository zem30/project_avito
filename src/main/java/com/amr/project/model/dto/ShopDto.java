package com.amr.project.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ShopDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String description;

    private CountryDto location;

    private List<ItemDto> items;

    private ImageDto logo;

    private double rating;

}
