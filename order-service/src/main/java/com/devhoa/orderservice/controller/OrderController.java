package com.devhoa.orderservice.controller;

import com.devhoa.orderservice.dto.request.OrderRequest;
import com.devhoa.orderservice.dto.response.ApiResponse;
import com.devhoa.orderservice.dto.response.OrderResponseDTO;
import com.devhoa.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ApiResponse<OrderResponseDTO> placeOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponseDTO orderResponseDTO = orderService.placeOrder(orderRequest);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Order Place Successfully", orderResponseDTO);
    }

}
