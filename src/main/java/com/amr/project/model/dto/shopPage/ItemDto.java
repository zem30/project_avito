package com.amr.project.model.dto.shopPage;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ItemDto {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String[] categoriesName;

    private Integer count;

    private BigDecimal price;

    private List<ImageDto> images;

    private Double rating;

    private String description;

    @NonNull
    private String shopName;


}
