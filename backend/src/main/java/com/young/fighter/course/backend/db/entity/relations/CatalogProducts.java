package com.young.fighter.course.backend.db.entity.relations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "course.catalog_products")
public class CatalogProducts {
    @Column("catalog_id")
    private Long catalogId;
    @Column("product_id")
    private Long productId;
}
