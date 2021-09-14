package com.amr.project.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ShopPageImageDto {

    private Long id;

    private byte[] picture;

}
