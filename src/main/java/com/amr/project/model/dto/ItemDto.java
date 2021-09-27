package com.amr.project.model.dto;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ItemDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private List<ImageDto> images = new ArrayList<>();

    private Double rating;

    private String description;

}
