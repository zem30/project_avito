package com.amr.project.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ShopDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String description;
    private double rating;
    private String username;
    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    private boolean isPretendentToBeDeleted;
    private List<ItemDto> items;
    private List<ReviewDto> reviews;
    private ImageDto logo;
}
