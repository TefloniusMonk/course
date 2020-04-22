package com.young.fighter.course.backend.db.repository.data;

import com.young.fighter.course.backend.db.entity.Bill;
import com.young.fighter.course.backend.db.repository.BillRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BillData implements BillRepository {
    private List<Bill> bills = Arrays.asList(
            new Bill(1L, 2L, Collections.emptyList()),
            new Bill(2L, 2L, Collections.emptyList()),
            new Bill(3L, 2L, Collections.emptyList()),
            new Bill(4L, 2L, Collections.emptyList())
    );

    @Override
    public Bill save(Bill view) {
        bills.add(view);
        return view;
    }

    @Override
    public void delete(Long id) {
        bills.removeIf(bill -> bill.getBillId().equals(id));
    }

    @Override
    public Bill findById(Long id) {
        return bills.stream()
                .filter(bill -> bill.getBillId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Bill> findAll() {
        return bills;
    }
}