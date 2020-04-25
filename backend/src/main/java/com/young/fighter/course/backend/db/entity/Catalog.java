package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "course", name = "catalog")
public class Catalog extends BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long catalogId;

    private String catalogName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "catalog_products",
            schema = "course",
            joinColumns = @JoinColumn(name = "catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
