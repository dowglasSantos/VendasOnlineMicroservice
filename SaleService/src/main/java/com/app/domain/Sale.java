package com.app.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "client_id", nullable = false)
    private Long client_id;

    @Column(name = "product_id", nullable = false)
    private Long product_id;

    @ElementCollection
    @CollectionTable(
            name = "tb_sale_allProductQuantity",
            joinColumns = @JoinColumn(name = "sale_id")
    )
    private Set<ProductQuantity> allProductQuantity;

    @Column(name = "total_value", nullable = false)
    private BigDecimal total_value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Sale() {
        this.allProductQuantity = new HashSet<>();
        this.total_value = BigDecimal.ZERO;
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

    private void statusValidation() {
        if(this.status == Status.COMPLETED || this.status == Status.CANCELED) {
            throw new UnsupportedOperationException("IMPOSSIBLE TO CHANGE A COMPLETED OR CANCELED SALE");
        }
    }

    private void recalculateTotalValue() {
        this.total_value = BigDecimal.ZERO;

        for (ProductQuantity productQuantity : this.allProductQuantity) {
            this.total_value = this.total_value.add(productQuantity.getValue());
        }
    }

    public void addProduct(Product product, Long quantity) {

        statusValidation();

        for (ProductQuantity pq : allProductQuantity) {
            if (pq.getProduct_id().equals(product.getId())) {
                pq.add(quantity);
                recalculateTotalValue();
                return;
            }
        }

        ProductQuantity entity = new ProductQuantity();
        entity.setProduct_id(product.getId());
        entity.setProductValue(product.getValue());

        entity.add(quantity);

        allProductQuantity.add(entity);

        recalculateTotalValue();

    }

    public void removeProduct(Long productId) {

        statusValidation();

        allProductQuantity.removeIf(
                pq -> pq.getProduct_id().equals(productId)
        );

        recalculateTotalValue();
    }
}
