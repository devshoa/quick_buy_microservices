package com.devhoa.orderservice.dto.request;

import com.devhoa.orderservice.dto.response.OrderLineItemsResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderLineItemsResponseDTO> orderLineItemDtosList;

}
