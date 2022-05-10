package com.example.couriermobile.mapper;

import com.example.couriermobile.dto.AttachmentDto;
import com.example.couriermobile.dto.HumanFrontDto;
import com.example.couriermobile.entity.Human;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AttachmentMapper.class})
public interface HumanMapper {

    HumanFrontDto toDto(Human human);

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
