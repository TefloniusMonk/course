//package com.young.fighter.course.backend.web.controller;
//
//import com.young.fighter.course.backend.dto.CatalogView;
//import com.young.fighter.course.backend.service.api.CatalogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
//@RestController
//@RequestMapping("/catalog")
//public class CatalogController {
//    private CatalogService catalogService;
//
//    //Каталог сможет редактировать только админ
//    @Autowired
//    public CatalogController(CatalogService catalogService) {
//        this.catalogService = catalogService;
//    }
//
//    @PostMapping("/")
//    @PreAuthorize("hasAuthority('CATALOG_WRITE')")
//    public CatalogView save(@RequestBody @Valid CatalogView view) {
//        return catalogService.save(view);
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('CATALOG_WRITE')")
//    public void delete(@PathVariable @NotNull Long id) {
//        catalogService.delete(id);
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('CATALOG_VIEW')")
//    public CatalogView findById(@PathVariable Long id) {
//        return catalogService.findById(id);
//    }
//
//    @GetMapping("/")
//    @PreAuthorize("hasAuthority('CATALOG_VIEW')")
//    public List<CatalogView> findAll() {
//        return catalogService.findAll();
//    }
//}
