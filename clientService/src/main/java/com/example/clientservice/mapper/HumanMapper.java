package com.example.clientservice.mapper;

import com.example.clientservice.dto.AttachmentDto;
import com.example.clientservice.dto.HumanDto;
import com.example.clientservice.dto.HumanFrontDto;
import com.example.clientservice.entity.Human;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Locale;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AttachmentMapper.class})
public interface HumanMapper {
    @Named("password")
    default String password(String password){
        if(password==null){
            return null;
        }
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    @Mapping(source = "type", target = "userType")
    @Mapping(source = "password", target = "password", qualifiedByName = "password")
    Human humanDtoToHuman(HumanDto humanDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateHumanFromHumanDto(HumanDto humanDto, @MappingTarget Human human);

    HumanFrontDto humanToHumanFrontDto(Human human);
    List<HumanFrontDto> humanToHumanFrontDto(List<Human> humans);

    @AfterMapping
    default void url(@MappingTarget HumanFrontDto humanFrontDto, Human human){
        if(humanFrontDto.getPhoto()==null || human.getId()==null || human.getUserType()==null){
            humanFrontDto.setPhoto(AttachmentDto.builder()
                            .name("image-not-found")
                            .size(6306L)
                            .type("image/png")
                            .url("/api/assets/image-not-found.png")
                    .build());
            return;
        }
        humanFrontDto.getPhoto().setUrl("/api/client/"+human.getId()+"/photo");
    }
}
