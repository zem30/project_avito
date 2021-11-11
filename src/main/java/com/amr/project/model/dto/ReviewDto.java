package com.amr.project.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDto {
    private Long id;

    @NotBlank(message = "Введите отзыв для товара")
    private String text;

    @Positive()
    @Length(min = 1, max = 5, message = "Поставьте оценку от 1 до 5")
    private int rating;

    @NotBlank(message = "Введите плюсы для товара")
    private String dignity; //плюсы

    @NotBlank(message = "Введите минусы для товара")
    private String flaw; //минусы

    private List<ImageDto> logo;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long itemId;
    private String itemName;
    private Long shopId;
    private String shopName;
    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;

}
