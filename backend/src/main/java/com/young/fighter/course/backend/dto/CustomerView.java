package com.young.fighter.course.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerView implements Serializable {
    private Long customerId;
    private String email;
    private String fullName;
    private LocalDate birthDate;
    private BasketView basket;
    private Long userId;
}
