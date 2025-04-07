package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.model.api.dto.orders.OrderDto;
import com.example.demo.model.api.dto.orders.ProductDto;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Order createSampleOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerName("Test Customer");
        order.setOrderDate(String.valueOf(LocalDate.now()));

        Product product = new Product();
        product.setId(10L);
        product.setName("Pizza");
        product.setPrice(15.0);
        product.setOrder(order);

        List<Product> products = new ArrayList<>();
        products.add(product);
        order.setProducts(products);

        return order;
    }

    private OrderDto createSampleOrderDto() {
        OrderDto dto = new OrderDto();
        dto.setCustomerName("Test Customer");
        dto.setOrderDate(String.valueOf(LocalDate.now()));

        ProductDto productDto = new ProductDto(10L, "Pizza", 15.0);
        dto.setProducts(List.of(productDto));
        return dto;
    }

    @Test
    void testGetOrderById_Success() {
        Order order = createSampleOrder();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        OrderDto result = orderService.getOrderById(1L);

        assertEquals(order.getCustomerName(), result.getCustomerName());
        assertEquals(1, result.getProducts().size());
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.getOrderById(1L));
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(createSampleOrder()));

        List<OrderDto> result = orderService.getAllOrders();

        assertEquals(1, result.size());
        assertEquals("Test Customer", result.get(0).getCustomerName());
    }

    @Test
    void testCreateOrder() {
        OrderDto dto = createSampleOrderDto();
        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

        when(orderRepository.save(any(Order.class))).thenAnswer(i -> {
            Order o = i.getArgument(0);
            o.setId(1L);
            return o;
        });

        OrderDto result = orderService.createOrder(dto);

        verify(orderRepository).save(captor.capture());
        assertEquals("Test Customer", result.getCustomerName());
        assertEquals(1, result.getProducts().size());
    }

    @Test
    void testUpdateOrder_Success() {
        Order existingOrder = createSampleOrder();
        OrderDto updateDto = createSampleOrderDto();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        OrderDto result = orderService.updateOrder(1L, updateDto);

        assertEquals("Test Customer", result.getCustomerName());
    }

    @Test
    void testAddProductToOrder() {
        Order order = createSampleOrder(); // тепер з mutable list
        ProductDto newProduct = new ProductDto(null, "Burger", 10.0);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        OrderDto result = orderService.addProductToOrder(1L, newProduct);

        assertEquals(2, result.getProducts().size());
        assertTrue(result.getProducts().stream().anyMatch(p -> p.getName().equals("Burger")));
    }


    @Test
    void testRemoveProductFromOrder_Success() {
        Order order = createSampleOrder();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        OrderDto result = orderService.removeProductFromOrder(1L, 10L);

        assertEquals(0, result.getProducts().size());
    }

    @Test
    void testRemoveProductFromOrder_ProductNotFound() {
        Order order = createSampleOrder();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        assertThrows(EntityNotFoundException.class,
                () -> orderService.removeProductFromOrder(1L, 999L));
    }
}
