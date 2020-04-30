package com.young.fighter.course.backend.data;

import com.young.fighter.course.backend.db.entity.Basket;
import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.CustomerView;

import java.time.LocalDate;
import java.util.Collections;

public class CustomerData {
    public static Customer getCustomer(Basket basket, User user) {
        return new Customer(null, basket, "email", "Full Name", LocalDate.now().minusYears(40), user, Collections.emptyList());
    }

    public static CustomerView getCustomerView(Long basketView, Long userId) {
        return new CustomerView(null, "email", "Full Name", LocalDate.now().minusYears(40), basketView, userId);
    }
}
