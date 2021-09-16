package com.amr.project.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ItemDto {
    private Long id;
    private String description;
    private boolean isModerateAccept;
    private boolean isModerated;
    private boolean isPretendentToBeDeleted;
    private String moderatedRejectReason;
    private String name;
    private BigDecimal price;
    private double rating;
    private List<ImageDto> images;
    private List<CategoryDto> categories;
    private List<ReviewDto> reviews;
    private Long shopId;
}
