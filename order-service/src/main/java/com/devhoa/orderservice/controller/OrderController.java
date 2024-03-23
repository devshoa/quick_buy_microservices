package com.devhoa.orderservice.controller;

import com.devhoa.orderservice.dto.request.OrderRequest;
import com.devhoa.orderservice.dto.response.ApiResponse;
import com.devhoa.orderservice.dto.response.OrderResponseDTO;
import com.devhoa.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ApiResponse<OrderResponseDTO> placeOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponseDTO orderResponseDTO = orderService.placeOrder(orderRequest);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Order Place Successfully", orderResponseDTO);
    }

    @GetMapping
    public ApiResponse<List<OrderResponseDTO>> getAlls() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", orderService.findAll());
    }

}
