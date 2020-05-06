package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
//    Mono<Customer> findByUserUserId(Long userId);
}
