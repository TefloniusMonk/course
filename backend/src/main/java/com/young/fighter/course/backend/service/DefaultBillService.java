package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Bill;
import com.young.fighter.course.backend.db.entity.Product;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    @Transactional
    public BillView sale(BasketView view) {
        List<Long> productIds = view.getProducts().stream().map(ProductView::getProductId).collect(Collectors.toList());
        if (!productService.allExist(productIds)
                || !customerService.exist(view.getCustomerId())
                || !customerService.getById(view.getCustomerId()).getBasket().getBasketId().equals(view.getBasketId())) {
            log.error("Product or customer doesn't exist, cannot carry out sale operation");
            throw new BusinessLogicException("entity.not.exist");
        }
        BillView billView = mapper.map(billRepository.save(new Bill(null,
                        customerService.getById(view.getCustomerId()),
                        view.getProducts().stream().map(productView -> mapper.map(productView, Product.class)).collect(Collectors.toList()),
                        LocalDateTime.now(),
                        productService.countSum(productIds)
                )),
                BillView.class);
        log.info("Created new bill: {}", billView);
        basketService.clear(view.getBasketId());
        return billView;
    }

    @Override
    public BillView findById(Long id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        if (optionalBill.isPresent()) {
            return mapper.map(optionalBill.get(), BillView.class);
        } else {
            log.error("Can not find bill with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    public List<BillView> findAll(Long customerId) {
        return billRepository.findAllByCustomerCustomerId(customerId).stream()
                .map(entity -> mapper.map(entity, BillView.class))
                .collect(Collectors.toList());
    }
}
