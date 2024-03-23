package com.devhoa.productservice.controller;

import com.devhoa.productservice.dto.request.ProductRequest;
import com.devhoa.productservice.dto.response.ApiResponse;
import com.devhoa.productservice.dto.response.ProductResponseDTO;
import com.devhoa.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponseDTO> createProduct(@RequestBody ProductRequest products) {
        ProductResponseDTO productResponseDTO = productService.createProduct(products);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Success",
                productResponseDTO
        );
    }

    @GetMapping
    public ApiResponse<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", products);
    }

}
