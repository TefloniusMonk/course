package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer entity);

    void delete(Long id);

    Customer findById(Long id);

    List<Customer> findAll();
}
