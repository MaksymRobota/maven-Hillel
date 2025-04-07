package com.example.demo.controller;

import com.example.demo.model.api.dto.orders.OrderDto;
import com.example.demo.model.api.dto.orders.ProductDto;
import com.example.demo.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrdersController {
    OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto order) {
        return orderService.updateOrder(id, order);
    }

    @PatchMapping("/{orderId}/products")
    public OrderDto addProductToOrder(@PathVariable Long orderId, @RequestBody ProductDto product) {
        return orderService.addProductToOrder(orderId, product);
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public OrderDto removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return orderService.removeProductFromOrder(orderId, productId);
    }
}
