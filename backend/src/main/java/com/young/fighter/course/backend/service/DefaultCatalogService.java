package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Catalog;
import com.young.fighter.course.backend.db.repository.CatalogRepository;
import com.young.fighter.course.backend.dto.CatalogView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.CatalogService;
import com.young.fighter.course.backend.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public CatalogView save(CatalogView view) {
        view.getProducts().forEach(productView -> productService.findById(productView.getProductId()));
        if (catalogRepository.findById(view.getCatalogId()).isPresent()) {
            log.info("Updating catalog with id: {}", view.getCatalogId());
            return modelMapper.map(catalogRepository.save(modelMapper.map(view, Catalog.class)), CatalogView.class);
        } else if (view.getCatalogId() == null) {
            CatalogView saved = modelMapper.map(catalogRepository.save(modelMapper.map(view, Catalog.class)), CatalogView.class);
            log.info("Saved new catalog: {}", saved.toString());
            return saved;
        } else {
            log.error("Cannot find product with id: {}", view.getCatalogId());
            throw new BusinessLogicException("entity.not.exist");
        }
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
