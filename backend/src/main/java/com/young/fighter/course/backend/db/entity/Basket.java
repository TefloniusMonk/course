package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "course.basket")
public class Basket extends BusinessEntity {
    @Id
    @Column("basket_id")
    private Long basketId;

    @Column("customer_id")
    private Long customerId;

    @Column("total_cost")
    private Long totalCost;
}
