package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Bill;

import java.util.List;

public interface BillRepository {
    Bill save(Bill view);

    void delete(Long id);

    Bill findById(Long id);

    List<Bill> findAll();
}
