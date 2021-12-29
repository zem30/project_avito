package com.amr.project.model.dto;


import com.amr.project.model.entity.User;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class AdvertDto {
    private Long id;
    @NotBlank(message = "Введите название объявления")
    private String name;
    @NotBlank(message = "Введите описание")
    private String description;
    @Positive(message = "Введите стоимость большее нуля")
    @NotNull(message = "Введите стоимость")
    private BigDecimal price;
    private List<CategoryDto>  categories;
    private Long userId;
    private String[] categoriesName;
    private boolean isPretendentToBeDeleted;
    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;
    @NotEmpty(message = "Выберите изображение")
    private List<ImageDto> images;
}
