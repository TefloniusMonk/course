package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("course.bill")
public class Bill extends BusinessEntity {
    @Id
    @Column("bill_id")
    private Long billId;

    @Column("customer_id")
    private Long customerId;

    @Column("sale_date_time")
    private LocalDateTime saleDateTime;

    @Column("total_sum")
    private Long totalSum;
}
