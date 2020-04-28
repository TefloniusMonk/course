package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.service.api.CustomerService;
import com.young.fighter.course.backend.service.api.UserHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    private final UserHolderService userHolderService;

    @Autowired
    public CustomerController(CustomerService customerService, UserHolderService userHolderService) {
        this.customerService = customerService;
        this.userHolderService = userHolderService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('PROFILE_WRITE')")
    public CustomerView save(@RequestBody @Valid CustomerView view) {
        return customerService.save(view);
    }


    @GetMapping("/")
    @PreAuthorize("hasAuthority('PROFILE_VIEW')")
    public CustomerView findById() {
        return customerService.findByUserId(userHolderService.getUserFromContext().getUserId());
    }
}
