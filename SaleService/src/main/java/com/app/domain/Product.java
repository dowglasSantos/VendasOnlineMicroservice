package com.app.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;

    private Long code;

    private String name;

    private String description;

    private BigDecimal value;

    private String status;
}