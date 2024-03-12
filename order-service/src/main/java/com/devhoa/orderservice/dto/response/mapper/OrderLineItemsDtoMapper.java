package com.devhoa.orderservice.dto.response.mapper;

import com.devhoa.orderservice.dto.response.OrderLineItemsResponseDTO;
import com.devhoa.orderservice.model.OrderLineItems;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderLineItemsDtoMapper implements Function<OrderLineItems, OrderLineItemsResponseDTO> {
    @Override
    public OrderLineItemsResponseDTO apply(OrderLineItems orderLineItems) {
        return new OrderLineItemsResponseDTO(
                orderLineItems.getId(),
                orderLineItems.getSkuCode(),
                orderLineItems.getPrice(),
                orderLineItems.getQuantity()
        );
    }

    public OrderLineItems convertOrderLineItems(OrderLineItemsResponseDTO orderLineItemsResponseDTO) {
        return new OrderLineItems(
                orderLineItemsResponseDTO.getId(),
                orderLineItemsResponseDTO.getSkuCode(),
                orderLineItemsResponseDTO.getPrice(),
                orderLineItemsResponseDTO.getQuantity()
        );
    }
}
