package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.service.api.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

//    @PostMapping("/")  Чек будет генерироваться автоматически и его нельяза будет удалить.
//    public BillView save(@RequestBody BillView view) {
//        return billService.save(view);
//    }
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        billService.delete(id);
//    }

    @GetMapping("/{id}")
    public BillView findById(@PathVariable Long id) {
        return billService.findById(id);
    }

    @GetMapping("/")
    public List<BillView> findAll() {
        return billService.findAll();
    }
}
