package com.amr.project.model.dto.shopPage;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class ImageDto {

    private Long id;

    private byte[] picture;

}
