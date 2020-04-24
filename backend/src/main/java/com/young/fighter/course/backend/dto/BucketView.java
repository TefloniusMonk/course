package com.young.fighter.course.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
public class BucketView implements Serializable {
    private Long bucketId;
    private CustomerView customer;
    private Set<ProductView> products;
    private Long totalCost;
}
