package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
