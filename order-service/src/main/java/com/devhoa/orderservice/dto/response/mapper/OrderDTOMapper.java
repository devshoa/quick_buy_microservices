package com.devhoa.orderservice.dto.response.mapper;

import com.devhoa.orderservice.dto.response.OrderResponseDTO;
import com.devhoa.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OrderDTOMapper implements Function<Order, OrderResponseDTO> {

    private final OrderLineItemsDtoMapper orderLineItemsDtoMapper;

    @Override
    public OrderResponseDTO apply(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getOrderLineItemsList().stream().map(orderLineItemsDtoMapper).toList()
        );
    }
}
