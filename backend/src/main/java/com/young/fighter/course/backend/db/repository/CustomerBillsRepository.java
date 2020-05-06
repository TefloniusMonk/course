package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.relations.CustomerBills;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerBillsRepository extends ReactiveCrudRepository<CustomerBills, Long> {
}
