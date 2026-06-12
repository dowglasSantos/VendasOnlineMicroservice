package com.app.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;

    private Long code;

    private String name;

    private String description;

    private BigDecimal value;

    private String status;

    public enum Status{
        ACTIVE, INACTIVE;
    }
}