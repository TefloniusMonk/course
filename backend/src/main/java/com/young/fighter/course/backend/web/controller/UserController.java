package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.service.api.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public String save(@RequestBody UserView view) {
        return userService.save(view).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id) {
        return userService.findById(id).toString();
    }
}
