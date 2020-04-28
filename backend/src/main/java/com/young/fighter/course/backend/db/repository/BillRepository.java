package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByCustomerCustomerId(Long customerId);

    void deleteAllByCustomerCustomerId(Long customerId);
}
