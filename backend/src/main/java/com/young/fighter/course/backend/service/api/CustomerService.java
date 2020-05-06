package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.dto.CustomerView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<CustomerView> save(CustomerView view, Long userId);

    Mono<Customer> save(Customer customer);

    void delete(Long id);

    Mono<CustomerView> findByUserId(Long id);

    Flux<CustomerView> findAll();

    boolean exist(Long id);

    Mono<Customer> getById(Long id);
}
