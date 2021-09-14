package com.amr.project.model.dto;
import com.amr.project.model.entity.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ShopPageDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String description;

    private Country location;

    private List<ShopPageItemDto> items = new ArrayList<>();

    private Image logo;

    private double rating;

}
