package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.db.repository.CustomerRepository;
import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.CustomerService;
import com.young.fighter.course.backend.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public CustomerView save(CustomerView view) {
        User user = userService.findById(view.getUserId());
        if (view.getCustomerId() != null) {
            if (customerRepository.findById(view.getCustomerId()).isPresent()) {
                if (!view.getUserId().equals(user.getUserId())) {
                    log.error("Do not try change user!");
                    throw new BusinessLogicException("entity.not.exist");
                }
                CustomerView customerView = mapper.map(customerRepository.save(
                        mapper.map(view, Customer.class)
                        )
                        , CustomerView.class);
                log.info("Updating customer: {}", customerView.toString());
                return customerView;
            } else {
                log.error("Cannot find customer with id: {}", view.getCustomerId());
                throw new BusinessLogicException("entity.not.exist");
            }
        }
        Customer customer = customerRepository.save(mapper.map(view, Customer.class));
        log.info("Creating customer: {}", customer.toString());
        user.setCustomer(customer);
        userService.save(user);
        return mapper.map(customer, CustomerView.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            userService.deleteByCustomerId(id);
            log.info("Deleting customer with id: {}", id);
            log.info("Deleting user with customerId: {}", id);
//            customerRepository.deleteById(id);
        } else {
            log.error("Can not delete customer with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    @Transactional
    public CustomerView findById(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();
            Hibernate.initialize(customer.getBasket());
            if (customer.getBasket() != null) {
                Hibernate.initialize(customer.getBasket().getProducts());
            }
            return mapper.map(customer, CustomerView.class);
        } else {
            log.error("Can not find customer with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }

    }

    @Override
    public List<CustomerView> findAll() {
        return customerRepository.findAll().stream()
                .map(entity -> mapper.map(entity, CustomerView.class))
                .collect(Collectors.toList());
    }
}
