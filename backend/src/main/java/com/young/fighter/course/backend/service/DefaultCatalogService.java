package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Catalog;
import com.young.fighter.course.backend.db.repository.CatalogRepository;
import com.young.fighter.course.backend.dto.CatalogView;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.CatalogService;
import com.young.fighter.course.backend.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultCatalogService implements CatalogService {
    private final CatalogRepository catalogRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public DefaultCatalogService(CatalogRepository catalogRepository, ProductService productService, ModelMapper modelMapper) {
        this.catalogRepository = catalogRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public CatalogView save(CatalogView view) {
        if (!view.getProducts().isEmpty()
                && !productService.allExist(view.getProducts().stream()
                .map(ProductView::getProductId)
                .collect(Collectors.toList()))) {
            log.error("Products not found");
            throw new BusinessLogicException("entity.not.exist");
        }
        if (view.getCatalogId() == null) {
            Catalog saved = catalogRepository.save(modelMapper.map(view, Catalog.class));
            Hibernate.initialize(saved.getProducts());
            log.info("Saved new catalog: {}", saved.toString());
            return modelMapper.map(saved, CatalogView.class);
        } else if (catalogRepository.findById(view.getCatalogId()).isPresent()) {
            log.info("Updating catalog with id: {}", view.getCatalogId());
            Catalog saved = catalogRepository.save(modelMapper.map(view, Catalog.class));
            Hibernate.initialize(saved.getProducts());
            return modelMapper.map(saved, CatalogView.class);
        } else {
            log.error("Cannot find catalog with id: {}", view.getCatalogId());
            throw new BusinessLogicException("entity.not.exist");
        }

    }

    @Override
    public void delete(Long id) {
        if (catalogRepository.findById(id).isPresent()) {
            catalogRepository.deleteById(id);
        } else {
            log.error("Cannot find catalog with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    @Transactional
    public CatalogView findById(Long id) {
        Optional<Catalog> catalogOptional = catalogRepository.findById(id);
        if (catalogOptional.isPresent()) {
            Catalog catalog = catalogOptional.get();
            Hibernate.initialize(catalog.getProducts());
            return modelMapper.map(catalogOptional.get(), CatalogView.class);
        } else {
            log.error("Cannot find catalog with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    @Transactional
    public List<CatalogView> findAll() {
        return catalogRepository.findAll().stream()
                .map(catalog -> {
                    Hibernate.initialize(catalog.getProducts());
                    return modelMapper.map(catalog, CatalogView.class);
                })
                .collect(Collectors.toList());
    }
}
