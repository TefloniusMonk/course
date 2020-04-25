package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.service.api.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/")
    public BillView save(@RequestBody BasketView view) {
        return billService.sale(view);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        billService.delete(id);
//    }

    @GetMapping("/{customerId}/{id}")
    public BillView findById(@PathVariable Long customerId, @PathVariable Long id) {
        return billService.findById(id);
    }

    @GetMapping("/{customerId}/")
    public List<BillView> findAll(@PathVariable Long customerId) {
        return billService.findAll(customerId);
    }
}
