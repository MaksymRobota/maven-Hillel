package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.model.api.dto.orders.ProductDto;
import com.example.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Product savedProduct = productRepository.save(product);

        return new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice());
    }
}
