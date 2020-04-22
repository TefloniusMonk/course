package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.Catalog;

import java.util.List;

public interface CatalogRepository {
    Catalog save(Catalog entity);

    void delete(Long id);

    Catalog findById(Long id);

    List<Catalog> findAll();
}
