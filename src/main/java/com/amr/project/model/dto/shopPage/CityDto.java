package com.amr.project.model.dto.shopPage;

import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Country;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CityDto {

    private Long id;

    private String name;

    private List<AddressDto> addresses;
}
