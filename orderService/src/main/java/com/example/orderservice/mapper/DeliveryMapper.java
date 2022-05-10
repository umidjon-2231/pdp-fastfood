package com.example.orderservice.mapper;

import com.example.orderservice.dto.DeliveryFrontDto;
import com.example.orderservice.entity.Delivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, HumanMapper.class})
public interface DeliveryMapper {
    DeliveryFrontDto toDto(Delivery delivery);
}
