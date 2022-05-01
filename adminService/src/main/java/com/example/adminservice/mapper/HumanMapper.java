package com.example.adminservice.mapper;

import com.example.adminservice.dto.HumanDto;
import com.example.adminservice.entity.Human;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AttachmentMapper.class})
public interface HumanMapper {
    Human humanDtoToHuman(HumanDto humanDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHumanFromHumanDto(HumanDto humanDto, @MappingTarget Human human);
}
