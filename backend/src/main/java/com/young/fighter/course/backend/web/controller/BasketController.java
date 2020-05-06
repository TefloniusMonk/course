//package com.young.fighter.course.backend.web.controller;
//
//import com.young.fighter.course.backend.dto.BasketView;
//import com.young.fighter.course.backend.service.api.BasketService;
//import com.young.fighter.course.backend.service.api.UserHolderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/basket")
//public class BasketController {
//    private BasketService bucketService;
//    private final UserHolderService userHolderService;
//
//    @Autowired
//    public BasketController(BasketService bucketService, UserHolderService userHolderService) {
//        this.bucketService = bucketService;
//        this.userHolderService = userHolderService;
//    }
//
//    @PostMapping("/")
//    @PreAuthorize("hasAuthority('BASKET_WRITE')")
//    public BasketView save(@RequestBody @Valid BasketView view) {
//        return bucketService.saveToBasket(view);
//    }
//
//
//    @GetMapping("/")
//    @PreAuthorize("hasAuthority('BASKET_VIEW')")
//    public BasketView findById() {
//        return bucketService.findByUserId(userHolderService.getUserFromContext().getUserId());
//    }
//}
