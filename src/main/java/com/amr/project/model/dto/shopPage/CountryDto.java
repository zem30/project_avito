package com.amr.project.model.dto.shopPage;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder

public class CountryDto {

    private Long id;

    private String name;

    private List<CityDto> cities;

}