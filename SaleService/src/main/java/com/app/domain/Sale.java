package com.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "tb_sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private Long code;

    @Column(name = "clientId", unique = true, nullable = false)
    private Long clientId;

    @Column(name = "productId", nullable = false)
    private Set<ProductQuantity> productQuantity;

    @Column(name = "totalValue", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "dataOfSale", nullable = false)
    private ZonedDateTime dataOfSale;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    private Product product;

    public Sale() {
        productQuantity = new HashSet<>();
        dataOfSale = ZonedDateTime.now();
        product = new Product();
    }

    public enum Status {
        STARTED, COMPLETED, CANCELED;

        public static Status getStatus(String status) {
            for(Status s : Status.values()) {
                if(s.name().equals(status)) {
                    return s;
                }
            };

            return null;
        }
    }

    public void statusValidation() {
        if(this.status == Status.COMPLETED || this.status == Status.CANCELED) {
            throw new UnsupportedOperationException("IMPOSSIBLE TO CHANGE A COMPLETED OR CANCELED SALE");
        }
    }

    public void totalValueCalculation() {
        for(ProductQuantity productQuantity : this.productQuantity) {
            totalValue = totalValue.add(productQuantity.getValue());
        }
    }

    public void addProduct(Product product, Long quantity) {
        statusValidation();
    }

    public void removeProduct(Product product, Long quantity) {
        statusValidation();
    }
}
