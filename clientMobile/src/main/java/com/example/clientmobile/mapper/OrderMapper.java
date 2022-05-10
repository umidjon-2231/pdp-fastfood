package com.example.clientmobile.mapper;

import com.example.clientmobile.dto.OrderDto;
import com.example.clientmobile.dto.OrderProductDto;
import com.example.clientmobile.entity.Order;
import com.example.clientmobile.entity.OrderProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "product.id", target = "productId")
    OrderProductDto orderProductToOrderProductDto(OrderProduct orderProduct);

    OrderDto orderToOrderDto(Order order);
    List<OrderDto> orderToOrderDto(List<Order> order);
}
