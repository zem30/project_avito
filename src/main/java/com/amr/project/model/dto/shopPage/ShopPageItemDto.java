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
public class ShopPageItemDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private List<ShopPageImageDto> images;

    private Double rating;

    private String description;

}
