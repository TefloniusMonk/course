package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("course.customer")
public class Customer extends BusinessEntity {
    @Id
    @Column("customer_id")
    private Long customerId;

    @Column("basket_id")
    private Long basketId;

    @Column("email")
    private String email;

    @Column("full_name")
    private String fullName;

    @Column("birth_date")
    private LocalDate birthDate;

    @Column("user_id")
    private Long userId;
}
