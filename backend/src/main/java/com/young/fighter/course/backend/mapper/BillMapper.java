package com.young.fighter.course.backend.mapper;

import com.young.fighter.course.backend.db.entity.Bill;
import com.young.fighter.course.backend.dto.BillView;

import java.util.stream.Collectors;

public class BillMapper {

    public BillView map(Bill entity) {
        BillView view = new BillView();
        view.setBillId(entity.getBillId());
        view.setCustomerId(entity.getCustomerId());
        view.setProducts(entity.getProducts().stream().map(ProductMapper::map).collect(Collectors.toList()));
        return view;
    }

    public Bill map(BillView view) {
        Bill entity = new Bill();
        entity.setBillId(view.getBillId());
        entity.setCustomerId(view.getCustomerId());
        entity.setProducts(view.getProducts().stream().map(ProductMapper::map).collect(Collectors.toList()));
        return entity;
    }
}
