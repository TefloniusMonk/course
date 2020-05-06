package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Basket;
import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.repository.BasketRepository;
import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.BasketService;
import com.young.fighter.course.backend.service.api.CustomerService;
import com.young.fighter.course.backend.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultBasketService implements BasketService {
    private final BasketRepository basketRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final ModelMapper mapper;

    @Autowired
    public DefaultBasketService(BasketRepository basketRepository, CustomerService customerService, ProductService productService, ModelMapper mapper) {
        this.basketRepository = basketRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Mono<BasketView> saveToBasket(BasketView view) {
        if (!(productService.allExist(view.getProducts().stream().map(ProductView::getProductId).collect(Collectors.toList())))) {
            log.error("Can't add to basket, product doesn't exist");
            throw new BusinessLogicException("entity.not.exist");
        }
        Mono<Customer> customer = customerService.getById(view.getCustomerId())
                .map(entity -> {
                    return entity;
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Can't find customer");
                    throw new BusinessLogicException("forbidden");
                }));
        if (view.getBasketId() != null) {
            return customer.doOnSuccess(cust -> {
                if (!basketRepository.findByBasketId(view.getBasketId())
                        && cust.getBasketId() != null
                        && cust.getBasketId().equals(view.getBasketId())) {
                    log.error("Customer was changed");
                    throw new BusinessLogicException("forbidden");
                }
                log.info("Update basket for customer: {}", view.getCustomerId());
            })
                    .then(basketRepository.save(mapper.map(view, Basket.class)))
                    .map(basket -> mapper.map(basket, BasketView.class));
        }
        return customer.flatMap(cust -> {
            log.info("Creating new basket for customer: {}", cust.getCustomerId());
            return basketRepository.save(
                    new Basket(null,
                            cust.getCustomerId(),
                            0L)
            )
                    .map(basket -> {
                        cust.setBasketId(basket.getBasketId());
                        customerService.save(cust);
                        return basket;
                    });
        })
                .map(basket -> mapper.map(basket, BasketView.class));

    }

    @Override
    public Mono<Basket> clear(Long basketId) {
        return basketRepository.findById(basketId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Can not find basket with id: {}", basketId);
                    throw new BusinessLogicException("entity.not.exist");
                }))
                .map(basket -> {
                    basket.setTotalCost(0L);
                    return basket;
                })
                .doOnSuccess(basketRepository::save);
    }

    @Override
    public Mono<BasketView> findByUserId(Long userId) {
        return customerService.findByUserId(userId)
                .map(CustomerView::getCustomerId)
//                .doOnSuccess(basketRepository::findByCustomer_CustomerId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Can not find basket with customerId: {}", userId);
                    throw new BusinessLogicException("entity.not.exist");
                }))
                .map(entity ->
                        mapper.map(entity, BasketView.class));
    }

    @Override
    public Mono<Basket> createNewBasket(Customer customer) {
        return basketRepository.save(new Basket(null, customer.getCustomerId(), 0L));
    }

}
