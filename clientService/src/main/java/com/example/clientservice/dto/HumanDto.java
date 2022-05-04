package com.example.clientservice.dto;

import com.example.clientservice.entity.enums.ClientStatus;
import com.example.clientservice.entity.enums.Language;
import com.example.clientservice.entity.enums.Region;
import com.example.clientservice.entity.enums.UserType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class HumanDto implements Serializable {
    private final String name, password;
    private final String number;
    private final ClientStatus status;
    private final LocalDate birthdate;
    private final Region region;
    private final Language lang;
    private final MultipartFile photo;
    private final UserType type;
}
