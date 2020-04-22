package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
