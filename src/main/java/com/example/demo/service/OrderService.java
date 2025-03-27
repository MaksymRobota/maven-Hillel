package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.model.api.dto.orders.OrderDto;
import com.example.demo.model.api.dto.orders.ProductDto;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return mapToDto(order);
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return List.of(orderDtos.toArray(new OrderDto[0]));
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomerName(orderDto.getCustomerName());
        order.setOrderDate(orderDto.getOrderDate());

        Order finalOrder = order;
        List<Product> products = orderDto.getProducts().stream()
                .map(p -> {
                    Product product = new Product();
                    product.setName(p.getName());
                    product.setPrice(p.getPrice());
                    product.setOrder(finalOrder);
                    return product;
                }).collect(Collectors.toList());

        order.setProducts(products);
        order = orderRepository.save(order);
        return mapToDto(order);
    }

    public OrderDto updateOrder(Long id, OrderDto updatedOrder) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        order.setCustomerName(updatedOrder.getCustomerName());
        order.setOrderDate(updatedOrder.getOrderDate());

        List<Product> updatedProducts = updatedOrder.getProducts().stream()
                .map(productDto -> new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), order))
                .collect(Collectors.toList());
        order.setProducts(updatedProducts);

        Order savedOrder = orderRepository.save(order);

        return mapToDto(savedOrder);
    }

    public OrderDto addProductToOrder(Long orderId, ProductDto productDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Product product = new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), order);
        order.getProducts().add(product);
        Order savedOrder = orderRepository.save(order);

        return mapToDto(savedOrder);
    }

    @Transactional
    public OrderDto removeProductFromOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Product productToRemove = order.getProducts().stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product not found in the order"));

        order.getProducts().remove(productToRemove);
        Order updatedOrder = orderRepository.save(order);
        orderRepository.flush();

        return mapToDto(updatedOrder);
    }

    private OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setOrderDate(order.getOrderDate());

        List<ProductDto> products = order.getProducts().stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());

        dto.setProducts(products);
        return dto;
    }
}
