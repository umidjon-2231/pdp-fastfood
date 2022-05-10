package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderFrontDto;
import com.example.orderservice.dto.OrderProductDto;
import com.example.orderservice.dto.OrderProductFrontDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {
        DeliveryMapper.class, FilialMapper.class, HumanMapper.class, ProductMapper.class
})
public interface OrderMapper {
    @Mapping(source = "productId", target = "product.id")
    OrderProduct orderProductDtoToOrderProduct(OrderProductDto dto);
    OrderProductFrontDto orderProductToOrderProductFrontDto(OrderProduct orderProduct);

    @Mapping(source = "filialId", target = "filial.id")
    @Mapping(source = "clientId", target = "client.id")
    Order orderDtoToOrder(OrderDto orderDto);

    @InheritInverseConfiguration(name = "orderDtoToOrder")
    OrderDto orderToOrderDto(Order order);

    @InheritConfiguration(name = "orderDtoToOrder")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromOrderDto(OrderDto orderDto, @MappingTarget Order order);


    OrderFrontDto orderToOrderFrontDto(Order order);
    List<OrderFrontDto> orderToOrderFrontDto(List<Order> order);
}
