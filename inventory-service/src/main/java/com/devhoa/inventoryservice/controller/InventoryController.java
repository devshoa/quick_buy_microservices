package com.devhoa.inventoryservice.controller;

import com.devhoa.inventoryservice.dto.response.InventoryResponseDTO;
import com.devhoa.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    // http://localhost:8082/api/inventory?sku-code=iphone1-13&sku-code=iphone12-red
    @GetMapping
    public List<InventoryResponseDTO> isInStock(@RequestParam("skuCodes") List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }


}
