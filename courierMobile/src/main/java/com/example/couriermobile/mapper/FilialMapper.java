package com.example.couriermobile.mapper;

import com.example.couriermobile.dto.FilialDto;
import com.example.couriermobile.entity.Filial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilialMapper {
    FilialDto toDto(Filial filial);
}
