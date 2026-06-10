package com.app.service;

import com.app.domain.Product;
import com.app.domain.Product.Status;
import com.app.dto.ProductDTO;
import com.app.exception.EntityNotFoundException;
import com.app.repository.IProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {
    @Autowired
    IProductRepository productRepository;

    Product product = new Product();

    public Product createProduct(@Valid ProductDTO productDTO) {
        product.setCode(productDTO.code());
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setValue(productDTO.value());
        product.setStatus(productDTO.status());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, @Valid ProductDTO productDTO) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id.toString()));

        entity.setCode(productDTO.code());
        entity.setName(productDTO.name());
        entity.setDescription(productDTO.description());
        entity.setValue(productDTO.value());
        entity.setStatus(productDTO.status());

        return productRepository.save(entity);
    }

    public void inactiveIsProduct(Long id) {
        Product entity =  productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id.toString()));

        entity.setStatus(Status.INACTIVE);
        this.productRepository.save(entity);
    }

    public void activeIsProduct(Long id) {
        Product entity =  productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id.toString()));

        if(entity.getStatus().equals(Status.INACTIVE)) {
            entity.setStatus(Status.ACTIVE);
            this.productRepository.save(entity);
        } else {
            new RuntimeException("PRODUCT ALREADY ACTIVE");
        }
    }
}
