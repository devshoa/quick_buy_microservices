package com.devhoa.productservice.dto.response.mapper;

import com.devhoa.productservice.dto.response.ProductResponseDTO;
import com.devhoa.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductResponseDTO> {
    @Override
    public ProductResponseDTO apply(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
