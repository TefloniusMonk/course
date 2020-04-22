package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.repository.CustomerRepository;
import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.mapper.CustomerMapper;
import com.young.fighter.course.backend.service.api.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCustomerService implements CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper mapper = new CustomerMapper();

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerView save(CustomerView view) {
        if (view.getCustomerId() != null) {
            if (customerRepository.findById(view.getCustomerId()) != null) {
                return mapper.map(customerRepository.save(mapper.map(view)));
            } else {
                System.out.println("No such entity");
            }
        }
        return mapper.map(customerRepository.save(mapper.map(view)));
    }

    @Override
    public void delete(Long id) {
        if (customerRepository.findById(id) != null) {
            customerRepository.deleteById(id);
        } else {
            System.out.println("No such entity");
        }
    }

    @Override
    public CustomerView findById(Long id) {
        if (customerRepository.findById(id) != null) {
            return mapper.map(customerRepository.findById(id).orElseThrow(NullPointerException::new));
        } else {
            System.out.println("No such entity");
        }
        return null;
    }

    @Override
    public List<CustomerView> findAll() {
        return customerRepository.findAll().stream()
                .map(entity -> mapper.map(entity))
                .collect(Collectors.toList());
    }
}
