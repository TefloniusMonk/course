package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.dto.CustomerView;

import java.util.List;

public interface CustomerService {
    CustomerView save(CustomerView view);

    Customer save(Customer customer);

    void delete(Long id);

    CustomerView findById(Long id);

    List<CustomerView> findAll();

    boolean exist(Long id);

    Customer getById(Long id);
}
