package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "course", name = "product")
public class Product extends BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long productId;

    private Long price;

    private String productName;

    private String productDesc;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Bucket> buckets = new HashSet<Bucket>();

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Catalog> catalogs = new HashSet<Catalog>();
}
