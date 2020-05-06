package com.young.fighter.course.backend.db.entity.relations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "course.basket_products")
public class BasketProducts {
    @Column("basket_id")
    private Long basketId;
    @Column("product_id")
    private Long productId;
}
