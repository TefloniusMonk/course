package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.Basket;
import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.dto.BasketView;

public interface BasketService {
    BasketView saveToBasket(BasketView view);

    Basket clear(Long basketId);

    BasketView findByUserId(Long id);

    Basket createNewBasket(Customer customer);
}
