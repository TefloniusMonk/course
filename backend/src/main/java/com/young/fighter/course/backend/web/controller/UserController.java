package com.young.fighter.course.backend.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.service.api.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @PostMapping("/")
    public String save(@RequestBody String view) {
        return userService.save(objectMapper.readValue(view, UserView.class)).toString();
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
