package com.example.clientmobile.dto;

import com.example.clientmobile.entity.enums.ClientStatus;
import com.example.clientmobile.entity.enums.Language;
import com.example.clientmobile.entity.enums.Region;
import com.example.clientmobile.entity.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HumanFrontDto  {
    private String name;
    private String number;
    private ClientStatus status;
    private LocalDate birthdate;
    private Region region;
    private Language lang;
    private UserType type;
    private AttachmentDto photo;
}
