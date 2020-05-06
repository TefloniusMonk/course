package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.relations.CatalogProducts;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CatalogProductsRepository extends ReactiveCrudRepository<CatalogProducts, Long> {
}
