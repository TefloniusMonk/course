package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    boolean existsByBasketId(Long id);

    Optional<Basket> findByCustomerCustomerId(Long customerId);
}
