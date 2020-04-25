package com.young.fighter.course.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillView implements Serializable {
    private Long billId;
    private Long customerId;
    private List<ProductView> products;
    private LocalDateTime saleDateTime;
    private Long totalSum;
}
