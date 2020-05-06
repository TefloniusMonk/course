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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
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
    public Mono<CatalogView> save(CatalogView view) {
        if (!view.getProducts().isEmpty()
                && !productService.allExist(view.getProducts().stream()
                .map(ProductView::getProductId)
                .collect(Collectors.toList()))) {
            log.error("Products not found");
            throw new BusinessLogicException("entity.not.exist");
        }
        if (view.getCatalogId() == null) {
            return catalogRepository.save(modelMapper.map(view, Catalog.class))
                    .map(entity -> {
                        log.info("Saved new catalog: {}", entity.toString());
                        Hibernate.initialize(entity.getProducts());
                        return modelMapper.map(entity, CatalogView.class);
                    });
        } else {
            return catalogRepository.findById(view.getCatalogId())
                    .switchIfEmpty(Mono.defer(() -> {
                        log.error("Cannot find catalog with id: {}", view.getCatalogId());
                        throw new BusinessLogicException("entity.not.exist");
                    }))
                    .then(catalogRepository.save(modelMapper.map(view, Catalog.class)))
                    .map(entity -> {
                        log.info("Updating catalog with id: {}", view.getCatalogId());
                        Hibernate.initialize(entity.getProducts());
                        return modelMapper.map(entity, CatalogView.class);
                    });
        }
    }

    @Override
    public Mono<Void> delete(Long id) {
        return catalogRepository.deleteById(id)
                .doOnError(error -> {
                            log.error("Cannot find catalog with id: {}", id);
                            throw new BusinessLogicException("entity.not.exist");
                        }
                );
    }

    @Override
    @Transactional
    public Mono<CatalogView> findById(Long id) {
        return catalogRepository.findById(id)
                .map(entity -> {
                    Hibernate.initialize(entity.getProducts());
                    return modelMapper.map(entity, CatalogView.class);
                }).switchIfEmpty(Mono.defer(() -> {
                    log.error("Cannot find catalog with id: {}", id);
                    throw new BusinessLogicException("entity.not.exist");
                }));
    }

    @Override
    @Transactional
    public Flux<CatalogView> findAll() {
        return catalogRepository.findAll()
                .map(entity -> {
                    Hibernate.initialize(entity.getProducts());
                    return modelMapper.map(entity, CatalogView.class);
                });
    }
}
