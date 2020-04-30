package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "course", name = "customer")
public class Customer extends BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Basket.class)
    private Basket basket;

    private String email;

    private String fullName;

    private LocalDate birthDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    @OneToMany(targetEntity = Bill.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "customer_bills",
            schema = "course",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "bill_id")
    )
    private List<Bill> bills;
}
