package com.app.domain;

import com.app.service.restclient.ProductService;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    private Set<ProductQuantity> allProductQuantity;

    @Column(name = "totalValue", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "dataOfSale", nullable = false)
    private ZonedDateTime dataOfSale;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Sale() {
        allProductQuantity = new HashSet<>();
        dataOfSale = ZonedDateTime.now();
        this.totalValue = BigDecimal.ZERO;
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

    private void totalValueCalculation() {
        for(ProductQuantity productQuantity : this.allProductQuantity) {
            totalValue = totalValue.add(productQuantity.getValue());
        }
    }

    private void recalculateTotalSaleValue() {
        statusValidation();
        BigDecimal totalValue = BigDecimal.ZERO;

        for(ProductQuantity productQuantity : this.allProductQuantity) {
            totalValue = totalValue.add(productQuantity.getValue());
        }
    }

    public void addProduct(Product product, Long quantity) {
        statusValidation();

        for(ProductQuantity productQuantitypq : this.allProductQuantity) {
            if(productQuantitypq.getProduct().getId().equals(product.getId())) {
                productQuantitypq.add(quantity);
            } else {
                ProductQuantity entity = new ProductQuantity();
                entity.setProduct(product);
                entity.setQuantity(quantity);

                allProductQuantity.add(entity);
            }
        }

        totalValueCalculation();
    }

    public void removeProduct(Long code) {
        statusValidation();

        for(ProductQuantity productQuantity : this.allProductQuantity) {
            if(productQuantity.getProduct().getCode().equals(code)) {
                allProductQuantity.remove(productQuantity);
            }
        }

        recalculateTotalSaleValue();
    }
}
