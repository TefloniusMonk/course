package com.young.fighter.course.backend.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
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
    private List<Basket> baskets;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<Catalog> catalogs;
}
