package com.example.couriermobile.dto;

import com.example.couriermobile.entity.enums.ClientStatus;
import com.example.couriermobile.entity.enums.Language;
import com.example.couriermobile.entity.enums.Region;
import com.example.couriermobile.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
