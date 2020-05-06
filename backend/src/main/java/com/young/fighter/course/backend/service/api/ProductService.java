package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.ProductView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {
    Mono<ProductView> save(ProductView view);

    Mono<Void> delete(Long id);

    Mono<ProductView> findById(Long id);

    Flux<ProductView> findAll();

    boolean allExist(List<Long> ids);

    Long countSum(List<Long> ids);
}
