package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.Basket;
import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.dto.BasketView;
import reactor.core.publisher.Mono;

public interface BasketService {
    Mono<BasketView> saveToBasket(BasketView view);

    Mono<Basket> clear(Long basketId);

    Mono<BasketView> findByUserId(Long id);

    Mono<Basket> createNewBasket(Customer customer);
}
