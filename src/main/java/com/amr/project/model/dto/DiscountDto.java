package com.amr.project.model.dto;

import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class DiscountDto {


    private Long id;


    private Integer minOrder;


    private Integer percentage;


    private Integer fixedDiscount;


    private ShopDto shop;


    private UserDto user;
}
