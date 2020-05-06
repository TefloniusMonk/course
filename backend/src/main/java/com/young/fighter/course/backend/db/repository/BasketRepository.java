package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Basket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BasketRepository extends ReactiveCrudRepository<Basket, Long> {
//    boolean findByBasketId(Long id);

//    Mono<Basket> findByCustomer_CustomerId(Long customerId);
}
