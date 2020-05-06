package com.young.fighter.course.backend.db.entity.relations;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "course.bill_products")
public class BillProducts {
    @Column("bill_id")
    private Long billId;
    @Column("product_id")
    private Long productId;
}
