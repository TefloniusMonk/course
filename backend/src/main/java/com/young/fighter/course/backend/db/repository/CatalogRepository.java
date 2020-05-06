package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Catalog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CatalogRepository extends ReactiveCrudRepository<Catalog, Long> {
}
