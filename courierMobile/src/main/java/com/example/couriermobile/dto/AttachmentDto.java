package com.example.couriermobile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttachmentDto {
    private String name, url, type;
    private Long size;
}
