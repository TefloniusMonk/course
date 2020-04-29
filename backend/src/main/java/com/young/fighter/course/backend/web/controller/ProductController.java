package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public ProductView save(@RequestBody @Valid ProductView view) {
        return productService.save(view);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public void delete(@PathVariable @NotNull Long id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_VIEW')")
    public ProductView findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/")
//    @PreAuthorize("hasAuthority('PRODUCT_VIEW')")
    public List<ProductView> findAll() {
        return productService.findAll();
    }
}
