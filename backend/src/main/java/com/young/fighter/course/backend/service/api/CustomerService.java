package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.CustomerView;

import java.util.List;

public interface CustomerService {
    CustomerView save(CustomerView view);

    void delete(Long id);

    CustomerView findById(Long id);

    List<CustomerView> findAll();
}
