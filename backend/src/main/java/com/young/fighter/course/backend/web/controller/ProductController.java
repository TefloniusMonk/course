package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.service.api.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ProductView save(@RequestBody ProductView view) {
        return productService.save(view);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public ProductView findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/")
    public List<ProductView> findAll() {
        return productService.findAll();
    }
}
