package com.devhoa.inventoryservice.service.impl;

import com.devhoa.inventoryservice.dto.response.InventoryResponseDTO;
import com.devhoa.inventoryservice.model.Inventory;
import com.devhoa.inventoryservice.repository.InventoryRepository;
import com.devhoa.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponseDTO> isInStock(List<String> skuCode) {
        int skuReq = skuCode.size();
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCode);
        int skuRes = inventories.size();
        List<InventoryResponseDTO> results = new ArrayList<>();
        for (Inventory inventory : inventories) {
            if (skuReq != skuRes) {
                throw new IllegalArgumentException("Sku code not valid");
            } else {
                results.add(InventoryResponseDTO.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build());
            }
        }
        return results;

    }
}
