package com.amr.project.model.dto;

import com.amr.project.model.entity.Shop;
import com.amr.project.model.enums.Gender;
import lombok.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class UserDto {

    private Long id;

    private String email;

    private String phone;

    private String firstName;

    private String lastName;

    private int age;

    private AddressDto address;

    private Gender gender;

    private Calendar birthday;

    private ImageDto images;

    private List<DiscountDto> discounts = new ArrayList<>();

    private List<ShopDto> shops;
}
