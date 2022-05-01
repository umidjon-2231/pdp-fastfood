package com.example.adminservice.mapper;

import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FilialMapper {
    Filial filialDtoToFilial(FilialDto filialDto);

    FilialDto filialToFilialDto(Filial filial);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFilialFromFilialDto(FilialDto filialDto, @MappingTarget Filial filial);
}
