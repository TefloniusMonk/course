package com.young.fighter.course.backend.db.repository.data;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;

public class CustomerData implements CustomerRepository {
    private List<Customer> customers = Arrays.asList(
            new Customer(1L, 1L),
            new Customer(2L, 2L),
            new Customer(3L, 3L),
            new Customer(4L, 4L),
            new Customer(6L, 5L)
    );

    @Override
    public Customer save(Customer entity) {
        customers.add(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        customers.removeIf(customer -> customer.getCustomerId().equals(id));
    }

    @Override
    public Customer findById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getCustomerId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }
}
