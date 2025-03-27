package com.example.demo.controller;

import com.example.demo.model.api.dto.orders.OrderDto;
import com.example.demo.model.api.dto.orders.ProductDto;
import com.example.demo.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto order) {
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }

    @PatchMapping("/{orderId}/products")
    public ResponseEntity<OrderDto> addProductToOrder(@PathVariable Long orderId, @RequestBody ProductDto product) {
        return ResponseEntity.ok(orderService.addProductToOrder(orderId, product));
    }

    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<OrderDto> removeProductFromOrder(@PathVariable Long orderId, @PathVariable Long productId) {
        return ResponseEntity.ok(orderService.removeProductFromOrder(orderId, productId));
    }
}
