package com.devhoa.orderservice.service.impl;

import com.devhoa.orderservice.dto.request.OrderRequest;
import com.devhoa.orderservice.dto.response.InventoryResponseDTO;
import com.devhoa.orderservice.dto.response.OrderResponseDTO;
import com.devhoa.orderservice.dto.response.mapper.OrderDTOMapper;
import com.devhoa.orderservice.dto.response.mapper.OrderLineItemsDtoMapper;
import com.devhoa.orderservice.model.Order;
import com.devhoa.orderservice.model.OrderLineItems;
import com.devhoa.orderservice.repository.OrderRepository;
import com.devhoa.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderLineItemsDtoMapper orderLineItemsDtoMapper;
    private final OrderDTOMapper orderDtoMapper;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public OrderResponseDTO placeOrder(OrderRequest orderRequest) {
        // trước khi thực hiện đặt hàng thì phải gọi đến inventory-service
        // để kiểm tra xem sản phẩm đó có còn hàng hay không
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemDtosList()
                        .stream()
                        .map(orderLineItemsDtoMapper::convertOrderLineItems)
                        .toList())
                .build();

        // thu thập tất cả các sku-code
        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponseDTO[] inventoryResponseDTOS = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDTO[].class)
                .block();


        assert inventoryResponseDTOS != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponseDTOS).allMatch(InventoryResponseDTO::isInStock);

        final Order createOrder;

        if (allProductsInStock) {
            createOrder = orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

        return orderDtoMapper.apply(createOrder);
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream().map(orderDtoMapper).toList();
    }

}
