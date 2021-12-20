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
public class DiscountDto {

    private Long id;

    private Integer minOrder;

    private Integer percentage;

    private Integer fixedDiscount;

    private Long shopId;

    private List<UserDto> users = new ArrayList<>();
}
