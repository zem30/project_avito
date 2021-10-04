package com.amr.project.model.dto;

import lombok.Data;

@Data
public class DiscountDto {

    private Long id;
    private Integer minOrder;
    private Integer percentage;
    private Integer fixedDiscount;
    private long shopId;
    private long userId;

}
