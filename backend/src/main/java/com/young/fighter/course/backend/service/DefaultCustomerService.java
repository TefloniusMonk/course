package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.repository.CustomerRepository;
import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultCustomerService implements CustomerService {
    private CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Autowired
    public DefaultCustomerService(CustomerRepository customerRepository, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }


    @Override
    public CustomerView save(CustomerView view) {
        if (view.getCustomerId() != null) {
            if (customerRepository.findById(view.getCustomerId()).isPresent()) {
                CustomerView customerView = mapper.map(customerRepository.save(
                        mapper.map(view, Customer.class)
                        )
                        , CustomerView.class);
                log.info("Creating new customer: {}", customerView.toString());
                return customerView;
            } else {
                log.error("Cannot find customer with id: {}", view.getCustomerId());
                throw new BusinessLogicException("entity.not.exist");
            }
        }
        log.info("Updating product: {}", view.toString());
        return mapper.map(customerRepository.save(mapper.map(view, Customer.class)), CustomerView.class);
    }

    @Override
    public void delete(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            log.info("Deleting customer with id: {}", id);
            customerRepository.deleteById(id);
        } else {
            log.error("Can not delete product with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    public CustomerView findById(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            return mapper.map(customerRepository.findById(id), CustomerView.class);
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
