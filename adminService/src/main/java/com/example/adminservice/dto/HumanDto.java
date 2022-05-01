package com.example.adminservice.dto;

import com.example.adminservice.entity.enums.ClientStatus;
import com.example.adminservice.entity.enums.Language;
import com.example.adminservice.entity.enums.Region;
import com.example.adminservice.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class HumanDto implements Serializable {
    private final String name;
    private final String number;
    private final ClientStatus status;
    private final LocalDate birthdate;
    private final Region region;
    private final Language lang;
    private final UserType userType;
    private final MultipartFile photo;
}
