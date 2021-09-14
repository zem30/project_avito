package com.amr.project.model.dto;
import com.amr.project.model.entity.*;
import lombok.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ShopDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String description;

    private Country location;

    private List<Item> items = new ArrayList<>();

    private List<Review> reviews;

    private Image logo;

    private double rating;

}
