package com.young.fighter.course.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductView implements Serializable {
    private Long productId;
    private Long cost;
    private String productName;
    private String productDesc;
}
