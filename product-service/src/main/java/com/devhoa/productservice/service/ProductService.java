package com.devhoa.productservice.service;

import com.devhoa.productservice.dto.request.ProductRequest;
import com.devhoa.productservice.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequest productRequest);
    List<ProductResponseDTO> getAllProducts();
}
