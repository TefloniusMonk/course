package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {
    @Id
    private Long billId;
    private Long customerId;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;
}
