package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Basket;
import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.db.repository.BasketRepository;
import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.BasketService;
import com.young.fighter.course.backend.service.api.CustomerService;
import com.young.fighter.course.backend.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
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
    public BasketView saveToBasket(BasketView view) {
        Customer customer = customerService.getById(view.getCustomerId());
        Hibernate.initialize(customer.getBasket());
        if (!(productService.allExist(view.getProducts().stream().map(ProductView::getProductId).collect(Collectors.toList())))) {
            log.error("Can't add to basket, product doesn't exist");
            throw new BusinessLogicException("entity.not.exist");
        }
        if (view.getBasketId() != null) {
            if (basketRepository.existsByBasketId(view.getBasketId())
                    && customer.getBasket() != null
                    && customer.getBasket().getBasketId().equals(view.getBasketId())) {
                log.info("Update basket for customer: {}", view.getCustomerId());
                return mapper.map(basketRepository.save(mapper.map(view, Basket.class)), BasketView.class);
            } else {
                log.error("Customer was changed");
                throw new BusinessLogicException("forbidden");
            }
        }
        log.info("Creating new basket for customer: {}", customer.getCustomerId());
        Basket newBasket = basketRepository.save(
                new Basket(null,
                        customer,
                        view.getProducts().stream()
                                .map(productView -> mapper.map(productView, Product.class))
                                .collect(Collectors.toList()),
                        0L)
        );
        customer.setBasket(newBasket);
        customerService.save(customer);
        return mapper.map(newBasket, BasketView.class);

    }

    @Override
    public Basket clear(Basket basket) {
        basket.setProducts(Collections.emptyList());
        return basketRepository.save(basket);
    }

    @Override
    public BasketView findByCustomerId(Long id) {
        return null;
    }

    @Override
    public Basket createNewBasket(Customer customer) {
        return basketRepository.save(new Basket(null, customer, null, 0L));
    }

}
