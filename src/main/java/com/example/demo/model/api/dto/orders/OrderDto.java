package com.example.demo.model.api.dto.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String customerName;
    private String orderDate;
    private List<ProductDto> products = new ArrayList<>();
}
