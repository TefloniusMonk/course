package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public CustomerView save(@RequestBody CustomerView view) {
        return customerService.save(view);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @GetMapping("/{id}")
    public CustomerView findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/")
    public List<CustomerView> findAll() {
        return customerService.findAll();
    }
}
