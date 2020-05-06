package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.BillView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BillService {
    Mono<BillView> sale(BasketView view);


    Mono<BillView> findById(Long userId, Long id);

    Flux<BillView> findAll(Long userId);

    void deleteAllByCustomerId(Long customerId);
}
