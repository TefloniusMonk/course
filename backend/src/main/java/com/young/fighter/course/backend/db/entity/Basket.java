package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "course", name = "basket")
public class Basket extends BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long basketId;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "basket_products",
            schema = "course",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "basket_id")
    )
    private List<Product> products;

    private Long totalCost;
}
