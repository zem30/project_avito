package com.amr.project.model.dto;

import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDto {
    Long id;
    String text;
    int rating;
    Long userId;
    String userFirstName;
    String userLastName;
    Long itemId;
    String itemName;
    Long shopId;
    String shopName;
    boolean isModerated;
    boolean isModerateAccept;
    String moderatedRejectReason;
}
