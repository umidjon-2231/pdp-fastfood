package com.example.clientmobile.mapper;

import com.example.clientmobile.dto.HumanDto;
import com.example.clientmobile.dto.HumanFrontDto;
import com.example.clientmobile.entity.Human;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AttachmentMapper.class})
public interface HumanMapper {
    @Named("password")
    default String password(String password){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    @Mapping(source = "password", target = "password", qualifiedByName = "password")
    Human humanDtoToHuman(HumanDto humanDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHumanFromHumanDto(HumanDto humanDto, @MappingTarget Human human);

    @Mapping(source = "photo.id", target = "photo.url", qualifiedByName = "url")
    HumanFrontDto humanToHumanFrontDto(Human human);
    List<HumanFrontDto> humanToHumanFrontDto(List<Human> humans);

    @Named("url")
    default String url(Long id){
        return "/api/client/"+id+"/photo";
    }
}
