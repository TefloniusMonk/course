package com.young.fighter.course.backend.web.controller;

import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.security.entity.JwtRequest;
import com.young.fighter.course.backend.service.api.UserHolderService;
import com.young.fighter.course.backend.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private final UserHolderService userHolderService;

    @Autowired
    public UserController(UserService userService, UserHolderService userHolderService) {
        this.userService = userService;
        this.userHolderService = userHolderService;
    }

    @PostMapping("/sign-up")
    public UserView save(@RequestBody @Valid UserView view) {
        return userService.save(view);
    }

    @PostMapping("/auth")
    public String auth(@RequestBody @Valid JwtRequest request) {
        return userService.auth(request);
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('PROFILE_WRITE')")
    public void delete() {
        userService.delete(userHolderService.getUserFromContext().getUserId());
    }
}
