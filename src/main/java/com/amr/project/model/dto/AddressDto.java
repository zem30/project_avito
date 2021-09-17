package com.amr.project.model.dto;

import com.amr.project.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class AddressDto {

    private Long id;

    private String cityIndex;

    private String street;

    private String house;


}
