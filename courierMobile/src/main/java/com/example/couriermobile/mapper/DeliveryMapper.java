package com.example.couriermobile.mapper;

import com.example.couriermobile.dto.DeliveryFrontDto;
import com.example.couriermobile.entity.Delivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, HumanMapper.class})
public interface DeliveryMapper {
    DeliveryFrontDto toDto(Delivery delivery);
}
