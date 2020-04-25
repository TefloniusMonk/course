package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.repository.BasketRepository;
import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.service.api.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultBasketService implements BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public DefaultBasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public BasketView saveToBucket(BasketView view) {
        return null;
    }

    @Override
    public void clear(Long id) {

    }

    @Override
    public BasketView findByCustomerId(Long id) {
        return null;
    }

}
