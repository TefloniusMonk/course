package com.young.fighter.course.backend.data;

import com.young.fighter.course.backend.db.entity.Basket;
import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.CustomerView;

import java.time.LocalDate;

public class CustomerData {
    public static Customer getCustomer(Basket basket, User user) {
        return new Customer(null, basket, "email", "Full Name", LocalDate.now().minusYears(40), user);
    }

    public static CustomerView getCustomerView(BasketView basketView, Long userId) {
        return new CustomerView(null, "email", "Full Name", LocalDate.now().minusYears(40), basketView, userId);
    }
}
