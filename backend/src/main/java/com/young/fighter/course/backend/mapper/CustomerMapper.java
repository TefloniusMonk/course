package com.young.fighter.course.backend.mapper;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.dto.CustomerView;

public class CustomerMapper {
    public CustomerView map(Customer entity) {
        CustomerView view = new CustomerView();
        view.setCustomerId(entity.getCustomerId());
        view.setUserId(entity.getUserId());
        return view;
    }

    public Customer map(CustomerView view) {
        Customer entity = new Customer();
        entity.setCustomerId(view.getCustomerId());
        entity.setUserId(view.getUserId());
        return entity;
    }
}
