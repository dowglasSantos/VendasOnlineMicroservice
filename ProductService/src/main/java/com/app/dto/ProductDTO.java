package com.app.dto;

import com.app.domain.Product;

import java.math.BigDecimal;

public record ProductDTO(Long code, String name, String description, BigDecimal value, Product.Status status) {}
