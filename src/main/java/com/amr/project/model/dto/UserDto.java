package com.amr.project.model.dto;

import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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

    private List<DiscountDto> discounts;
}
