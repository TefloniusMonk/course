package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public CustomerView save(@RequestBody @Valid CustomerView view) {
        return customerService.save(view);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        customerService.delete(id);
    }

    @GetMapping("/{id}")
    public CustomerView findById(@PathVariable @NotNull Long id) {
        return customerService.findById(id);
    }
}
