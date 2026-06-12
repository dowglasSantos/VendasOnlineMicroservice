package com.app.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class ProductQuantity {
    private Long product_id;

    private BigDecimal productValue;

    private Long quantity;

    private BigDecimal value;

    public ProductQuantity(){
        this.quantity = 0L;
        this.value = BigDecimal.ZERO;
    }

    public void add(Long quantity) {
        this.quantity += quantity;
        BigDecimal newValue = this.productValue.multiply(new BigDecimal(quantity));
        BigDecimal newTotal = this.value.add(newValue);
        this.value = newTotal;
    };

    public void removed(Long quantity) {
        this.quantity -= quantity;
        BigDecimal newValue = this.productValue.multiply(new BigDecimal(quantity));
        this.value = value.subtract(newValue);
    };
}
