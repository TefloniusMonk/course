package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Bill;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BillRepository extends ReactiveCrudRepository<Bill, Long> {
//    Flux<Bill> findAllByCustomerCustomerId(Long customerId);
//
//    void deleteAllByCustomerCustomerId(Long customerId);
}
