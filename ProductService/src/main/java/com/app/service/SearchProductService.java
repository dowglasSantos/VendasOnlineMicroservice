package com.project.app.service;

import com.project.app.domain.Product;
import com.project.app.domain.Product.Status;
import com.project.app.exception.EntityNotFoundException;
import com.project.app.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchProductService {
    @Autowired
    IProductRepository productRepository;

    public Page<Product> findAllPageProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findAllByStatus(Pageable pageable, Status status) {
        return productRepository.findAllByStatus(pageable, status);
    }

    public Product findByCode(Long code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "code", code.toString()));
    }
}
