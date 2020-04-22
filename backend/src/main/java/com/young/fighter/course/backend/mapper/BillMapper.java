package com.young.fighter.course.backend.mapper;

import com.young.fighter.course.backend.db.entity.Bill;
import com.young.fighter.course.backend.dto.BillView;

public class BillMapper {

    public BillView map(Bill entity) {
        BillView view = new BillView();
        ///
        return view;
    }

    public Bill map(BillView view) {
        Bill entity = new Bill();
        ///
        return entity;
    }
}
