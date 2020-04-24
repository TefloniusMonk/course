package com.young.fighter.course.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
public class CatalogView implements Serializable {
    private Long catalogId;
    private String catalogName;
    private Set<ProductView> products;
}
