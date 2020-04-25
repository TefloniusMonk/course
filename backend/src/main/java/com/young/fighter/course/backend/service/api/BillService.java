package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.BillView;

import java.util.List;

public interface BillService {
    BillView sale(BasketView view);

    void delete(Long id);

    BillView findById(Long id);

    List<BillView> findAll();
}
