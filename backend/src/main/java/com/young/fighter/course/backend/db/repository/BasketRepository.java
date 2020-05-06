package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Basket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BasketRepository extends ReactiveCrudRepository<Basket, Long> {
    boolean existsByBasketId(Long id);

    Mono<Basket> findByCustomerCustomerId(Long customerId);
}
