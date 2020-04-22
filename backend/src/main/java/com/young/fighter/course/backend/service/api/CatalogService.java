package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.CatalogView;

import java.util.List;

public interface CatalogService {
    CatalogView save(CatalogView view);

    void delete(Long id);

    CatalogView findById(Long id);

    List<CatalogView> findAll();
}
