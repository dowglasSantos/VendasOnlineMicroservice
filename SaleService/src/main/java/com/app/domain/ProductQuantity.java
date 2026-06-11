package com.app.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductQuantity {
    private Product product;

    private Long quantity;

    private BigDecimal value;

    public ProductQuantity(){
        this.quantity = 0L;
        this.value = BigDecimal.ZERO;
    }

    public void add(Long quantity) {
        this.quantity += quantity;
        BigDecimal newValue = this.product.getValue().multiply(new BigDecimal(quantity));
        BigDecimal newTotal = this.value.add(newValue);
        this.value = newTotal;
    };

    public void removed(Long quantity) {
        this.quantity -= quantity;
        BigDecimal newValue = this.product.getValue().multiply(new BigDecimal(quantity));
        this.value = value.subtract(newValue);
    };
}
