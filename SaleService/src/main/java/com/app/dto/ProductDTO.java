package com.app.dto;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        Long code,
        String name,
        String description,
        BigDecimal value,
        String status
)
{};