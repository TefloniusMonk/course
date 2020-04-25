package com.young.fighter.course.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketView implements Serializable {
    private Long basketId;
    private Long customerId;
    private List<ProductView> products;
    private Long totalCost;
}
