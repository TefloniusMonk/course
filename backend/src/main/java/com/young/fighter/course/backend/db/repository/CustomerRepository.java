package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
