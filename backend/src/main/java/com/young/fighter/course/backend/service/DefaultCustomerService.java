package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.repository.CustomerRepository;
import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.CustomerService;
import com.young.fighter.course.backend.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class DefaultCustomerService implements CustomerService {
    private CustomerRepository customerRepository;
    private final ModelMapper mapper;
    private UserService userService;

    @Autowired
    public DefaultCustomerService(CustomerRepository customerRepository, ModelMapper mapper, UserService userService) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.userService = userService;
    }


    @Override
    @Transactional
    public Mono<CustomerView> save(CustomerView view, Long userId) {
        if (view.getCustomerId() != null) {
            return customerRepository.findById(view.getCustomerId())
                    .switchIfEmpty(Mono.defer(() -> {
                        log.error("Cannot find customer with id: {}", view.getCustomerId());
                        throw new BusinessLogicException("entity.not.exist");
                    }))
                    .then(customerRepository.save(mapper.map(view, Customer.class)))
                    .zipWith(userService.findById(userId))
                    .flatMap(pair -> { // customer, user
                        if (!view.getUserId().equals(pair.getT2().getUserId())) {
                            log.error("Do not try change user!");
                            return Mono.error(new BusinessLogicException("forbidden"));
                        }
                        return Mono.just(mapper.map(pair.getT1(), CustomerView.class));
                    });
        }
        Customer customer = mapper.map(view, Customer.class);
        return userService.findById(userId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Cannot find user with id: {}", view.getCustomerId());
                    throw new BusinessLogicException("entity.not.exist");
                }))
                .map(user -> {
                    customer.setUserId(user.getUserId());
                    customerRepository.save(customer);
                    log.info("Creating customer: {}", customer.toString());
                    user.setCustomerId(customer.getCustomerId());
                    userService.save(user);
                    return mapper.map(customer, CustomerView.class);
                });
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Mono<Void> delete(Long id) {
        return customerRepository.findById(id).doOnError(error -> {
            log.error("Can not delete customer with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }).then(userService.deleteByCustomerId(id)
                .doOnError(error -> {
                    log.error("Can not delete user with customerId: {}", id);
                    throw new BusinessLogicException("entity.not.exist");
                })
        );
    }

    @Override
    @Transactional
    public Mono<CustomerView> findByUserId(Long userId) {
        return customerRepository.findByUserUserId(userId)
                .map(customer -> {
                    return mapper.map(customer, CustomerView.class);
                })
                .doOnError(error -> {
                    log.error("Can not find customer with userId: {}", userId);
                    throw new BusinessLogicException("entity.not.exist");
                });
    }

    @Override
    public Flux<CustomerView> findAll() {
        return customerRepository.findAll()
                .map(customer -> mapper.map(customer, CustomerView.class));
    }

    @Override
    public Mono<Boolean> exist(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public Mono<Customer> getById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    return customer;
                }).doOnError(error -> {
                    log.error("Can not find customer with id: {}", id);
                    throw new BusinessLogicException("entity.not.exist");
                });
    }
}
