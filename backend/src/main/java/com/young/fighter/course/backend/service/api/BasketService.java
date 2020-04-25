package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.BasketView;

public interface BasketService {
    BasketView saveToBucket(BasketView view);

    void clear(Long id);

    BasketView findByCustomerId(Long id);
}
