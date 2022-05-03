package com.example.clientmobile.dto;

import com.example.clientmobile.entity.enums.ClientStatus;
import com.example.clientmobile.entity.enums.Language;
import com.example.clientmobile.entity.enums.Region;
import com.example.clientmobile.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class HumanFrontDto implements Serializable {
    private final String name;
    private final String number;
    private final ClientStatus status;
    private final LocalDate birthdate;
    private final Region region;
    private final Language lang;
    private final UserType userType;
    private final AttachmentDto photo;
}
