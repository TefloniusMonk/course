package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.relations.BillProducts;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BillProductsRepository extends ReactiveCrudRepository<BillProducts, Long> {
}
