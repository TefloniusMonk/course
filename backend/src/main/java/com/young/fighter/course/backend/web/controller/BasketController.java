package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.service.api.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bucket")
public class BasketController {
    private BasketService bucketService;

    @Autowired
    public BasketController(BasketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("/")
    public BasketView save(@RequestBody @Valid BasketView view) {
        return bucketService.saveToBasket(view);
    }


    @GetMapping("/{id}")
    public BasketView findById(@PathVariable Long id) {
        return bucketService.findByCustomerId(id);
    }
}
