package com.amr.project.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImageDto {
    private Long id;
    private byte[] picture;
    private String url;


}