package com.young.fighter.course.backend.db.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("course.product")
public class Product extends BusinessEntity {
    @Id
    @Column("product_id")
    private Long productId;

    @Column("price")
    private Long price;

    @Column("product_name")
    private String productName;

    @Column("product_desc")
    private String productDesc;
}
