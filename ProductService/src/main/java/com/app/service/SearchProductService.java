package com.app.service;

import com.app.domain.Product;
import com.app.domain.Product.Status;
import com.app.exception.EntityNotFoundException;
import com.app.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchProductService {
    @Autowired
    IProductRepository productRepository;

    private Optional<Product> product;

    public Page<Product> findAllPageProduct() {
        Page<Product> page = productRepository.findAll(Pageable.unpaged());

        return page;
    }

    public Page<Product> findAllByStatus(Integer pageNumber, Integer pageSize, Status status) {
        Pageable page = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"));

        return productRepository.findAllByStatus(page, status);
    }

    public Product findByCode(Long code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(Product.class, "code", code.toString()));
    }

    public Optional<Product> IsActive() {
        return product = productRepository.findByStatus("ACTIVE");
    }
}
