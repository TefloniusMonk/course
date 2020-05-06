package com.young.fighter.course.backend.db.entity.relations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "course.customer_bills")
public class CustomerBills {
    @Column("customer_id")
    private Long customerId;
    @Column("bill_id")
    private Long billId;
}
