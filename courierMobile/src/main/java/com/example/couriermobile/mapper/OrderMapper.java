package com.example.couriermobile.mapper;

import com.example.couriermobile.dto.OrderDto;
import com.example.couriermobile.dto.OrderFrontDto;
import com.example.couriermobile.dto.OrderProductDto;
import com.example.couriermobile.dto.OrderProductFrontDto;
import com.example.couriermobile.entity.Order;
import com.example.couriermobile.entity.OrderProduct;
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
