package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public UserView save(@RequestBody @Valid UserView view) {
        return userService.save(view);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull Long id) {
        userService.delete(id);
    }

//    @GetMapping("/{id}")
//    public String findById(@PathVariable Long id) {
//        return userService.findById(id).toString();
//    }
}
