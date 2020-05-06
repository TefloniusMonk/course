package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.db.repository.ProductRepository;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;
    private final ModelMapper mapper;

    @Autowired
    public DefaultProductService(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<ProductView> save(ProductView view) {
        if (view.getProductId() != null) {
            return productRepository.findById(view.getProductId())
                    .switchIfEmpty(Mono.defer(() -> {
                        log.error("Cannot find product with id: {}", view.getProductId());
                        throw new BusinessLogicException("entity.not.exist");
                    }))
                    .then(productRepository.save(mapper.map(view, Product.class)))
                    .map(entity -> {
                        log.info("Creating new product: {}", entity.toString());
                        return mapper.map(entity, ProductView.class);
                    });
        }
        log.info("Updating product: {}", view.toString());
        return productRepository.save(mapper.map(view, Product.class))
                .map(entity -> mapper.map(entity, ProductView.class));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return productRepository.deleteById(id)
                .doOnError(error -> {
                    log.error("Can not delete product with id: {}", id);
                    throw new BusinessLogicException("entity.not.exist");
                });
    }

    @Override
    @Transactional
    public Mono<ProductView> findById(Long id) {
        return productRepository.findById(id)
                .map(entity -> {
                    return mapper.map(entity, ProductView.class);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Product with id {} doesn't exist", id);
                    throw new BusinessLogicException("entity.not.exist");
                }));
    }

    @Override
    @Transactional
    public Flux<ProductView> findAll() {
        return productRepository.findAll()
                .map(entity -> {
                    return mapper.map(entity, ProductView.class);
                });
    }

    @Override
    public boolean allExist(List<Long> ids) {
        return productRepository.existsAllByProductIdIn(ids);
    }

    @Override
    public Long countSum(List<Long> ids) {
        return productRepository.countSum(ids);
    }

}
