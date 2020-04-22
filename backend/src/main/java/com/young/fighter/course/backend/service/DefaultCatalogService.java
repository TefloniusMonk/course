package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.dto.CatalogView;
import com.young.fighter.course.backend.service.api.CatalogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCatalogService implements CatalogService {
    @Override
    public CatalogView save(CatalogView view) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CatalogView findById(Long id) {
        return null;
    }

    @Override
    public List<CatalogView> findAll() {
        return null;
    }
}
