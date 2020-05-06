package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Bill;
import com.young.fighter.course.backend.db.repository.BillRepository;
import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.BasketService;
import com.young.fighter.course.backend.service.api.BillService;
import com.young.fighter.course.backend.service.api.CustomerService;
import com.young.fighter.course.backend.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultBillService implements BillService {
    private BillRepository billRepository;
    private ModelMapper mapper;
    private final CustomerService customerService;
    private final ProductService productService;
    private final BasketService basketService;

    @Autowired
    public DefaultBillService(BillRepository billRepository, ModelMapper modelMapper, CustomerService customerService, ProductService productService, BasketService basketService) {
        this.billRepository = billRepository;
        this.mapper = modelMapper;
        this.customerService = customerService;
        this.productService = productService;
        this.basketService = basketService;
    }

    @Override
    public Mono<BillView> sale(BasketView view) {
        List<Long> productIds = view.getProducts().stream().map(ProductView::getProductId).collect(Collectors.toList());
        return customerService.getById(view.getCustomerId())
                .doOnError(error -> {
                    throw new BusinessLogicException("entity.not.exist");
                })
                .map(customer -> {
                    if (!customer.getBasketId().equals(view.getBasketId())
                            ||
                            !productService.allExist(productIds)) {
                        log.error("Product or customer doesn't exist, cannot carry out sale operation");
                        throw new BusinessLogicException("entity.not.exist");
                    }
                    BillView billView = mapper.map(billRepository.save(new Bill(null,
                                    customer.getCustomerId(),
                                    LocalDateTime.now(),
                                    productService.countSum(productIds)
                            )),
                            BillView.class);
                    log.info("Created new bill: {}", billView);
                    basketService.clear(view.getBasketId());
                    return billView;
                });
    }

    @Override
    public Mono<BillView> findById(Long userId, Long id) {
        return billRepository.findById(id)
                .zipWith(customerService.findByUserId(userId))
                .flatMap(pair -> { // (bill, customer)
                    if (!pair.getT2().getCustomerId().equals(pair.getT1().getCustomerId())) {
                        return Mono.error(new BusinessLogicException("forbidden"));
                    }
                    return Mono.just(mapper.map(pair.getT1(), BillView.class));
                }).doOnError(error -> {
                    log.error("Can not find bill with id: {}", id);
                    throw new BusinessLogicException("entity.not.exist");
                });
    }

    @Override
    public Flux<BillView> findAll(Long userId) {
        return customerService.findByUserId(userId)
                .flatMapMany(customerView -> billRepository.findAllByCustomerCustomerId(customerView.getCustomerId()))
                .map(bill -> mapper.map(bill, BillView.class));
    }

    @Override
    public void deleteAllByCustomerId(Long customerId) {
        billRepository.deleteAllByCustomerCustomerId(customerId);
    }
}
