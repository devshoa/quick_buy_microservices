package com.devhoa.orderservice.service;

import com.devhoa.orderservice.dto.request.OrderRequest;
import com.devhoa.orderservice.dto.response.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequest orderRequest);

    List<OrderResponseDTO> findAll();
}
