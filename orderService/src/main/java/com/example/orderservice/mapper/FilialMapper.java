package com.example.orderservice.mapper;

import com.example.orderservice.dto.FilialDto;
import com.example.orderservice.entity.Filial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilialMapper {
    FilialDto toDto(Filial filial);
}
