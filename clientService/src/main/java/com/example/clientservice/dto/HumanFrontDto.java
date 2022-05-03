package com.example.clientservice.dto;

import com.example.clientservice.entity.enums.ClientStatus;
import com.example.clientservice.entity.enums.Language;
import com.example.clientservice.entity.enums.Region;
import com.example.clientservice.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class HumanFrontDto {
    private String name;
    private String number;
    private ClientStatus status;
    private LocalDate birthdate;
    private Region region;
    private Language lang;
    private UserType userType;
    private AttachmentDto photo;
}
