package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.BucketView;
import com.young.fighter.course.backend.service.api.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/bucket")
public class BucketController {
    private BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("/")
    public BucketView save(@RequestBody @Valid BucketView view) {
        return bucketService.saveToBucket(view);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        bucketService.clear(id);
    }

    @GetMapping("/{id}")
    public BucketView findById(@PathVariable Long id) {
        return bucketService.findByCustomerId(id);
    }

}
