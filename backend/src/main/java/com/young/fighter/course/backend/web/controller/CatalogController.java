package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.CatalogView;
import com.young.fighter.course.backend.service.api.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    private CatalogService catalogService;

    //Каталог сможет редактировать только админ
    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/")
    public CatalogView save(@RequestBody @Valid CatalogView view) {
        return catalogService.save(view);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        catalogService.delete(id);
    }

    @GetMapping("/{id}")
    public CatalogView findById(@PathVariable Long id) {
        return catalogService.findById(id);
    }

    @GetMapping("/")
    public List<CatalogView> findAll() {
        return catalogService.findAll();
    }
}
