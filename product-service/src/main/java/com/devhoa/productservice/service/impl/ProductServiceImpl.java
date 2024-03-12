package com.devhoa.productservice.service.impl;


import com.devhoa.productservice.dto.request.ProductRequest;
import com.devhoa.productservice.dto.response.ProductResponseDTO;
import com.devhoa.productservice.dto.response.mapper.ProductDTOMapper;
import com.devhoa.productservice.model.Product;
import com.devhoa.productservice.repository.ProductRepository;
import com.devhoa.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    @Override
    public ProductResponseDTO createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        return productDTOMapper.apply(productRepository.save(product));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productDTOMapper).toList();
    }
}
