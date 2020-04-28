package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserUserId(Long userId);
}
