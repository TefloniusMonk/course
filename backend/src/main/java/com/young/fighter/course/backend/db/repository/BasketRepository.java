package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    boolean existsByBasketId(Long id);
}
