package com.amr.project.model.dto.shopPage;

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
