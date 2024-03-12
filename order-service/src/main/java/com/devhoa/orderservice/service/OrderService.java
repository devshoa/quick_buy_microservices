package com.devhoa.orderservice.service;

import com.devhoa.orderservice.dto.request.OrderRequest;
import com.devhoa.orderservice.dto.response.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequest orderRequest);
}
