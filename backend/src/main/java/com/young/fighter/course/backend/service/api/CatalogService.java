package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.CatalogView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogService {
    Mono<CatalogView> save(CatalogView view);

    Mono<Void> delete(Long id);

    Mono<CatalogView> findById(Long id);

    Flux<CatalogView> findAll();
}
