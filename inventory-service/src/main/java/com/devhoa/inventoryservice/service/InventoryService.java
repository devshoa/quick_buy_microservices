package com.devhoa.inventoryservice.service;

import com.devhoa.inventoryservice.dto.response.InventoryResponseDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseDTO> isInStock(List<String> skuCode);
}
