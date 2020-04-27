package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "course", name = "bill")
public class Bill extends BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bill_id")
    private Long billId;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bill_products",
            schema = "course",
            joinColumns = @JoinColumn(name = "bill_bill_id"),
            inverseJoinColumns = @JoinColumn(name = "products_product_id")
    )
    private List<Product> products;

    private LocalDateTime saleDateTime;

    @Column(name = "total_sum")
    private Long totalSum;
}
