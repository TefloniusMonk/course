package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.service.api.BillService;
import com.young.fighter.course.backend.service.api.UserHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private BillService billService;
    private final UserHolderService userHolderService;

    @Autowired
    public BillController(BillService billService, UserHolderService userHolderService) {
        this.billService = billService;
        this.userHolderService = userHolderService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('BILL_VIEW')")
    public BillView save(@RequestBody BasketView view) {
        return billService.sale(view);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('BILL_VIEW')")
    public BillView findById(@PathVariable Long id) {
        return billService.findById(userHolderService.getUserFromContext().getUserId(), id);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('BILL_VIEW')")
    public List<BillView> findAll() {
        return billService.findAll(userHolderService.getUserFromContext().getUserId());
    }
}
