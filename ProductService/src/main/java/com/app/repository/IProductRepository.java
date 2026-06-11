package com.app.repository;

import com.app.domain.Product;
import com.app.domain.Product.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Optional<Product> findByCode(Long code);
    Optional<Product> findByStatus(String status);
    Page<Product> findAllByStatus(Pageable pageable, Status status);
}
