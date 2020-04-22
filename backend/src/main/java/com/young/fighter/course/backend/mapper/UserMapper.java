package com.young.fighter.course.backend.mapper;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.UserView;

public class UserMapper {
    public UserView map(User entity) {
        UserView view = new UserView();
        view.setUserId(entity.getUserId());
        view.setLogin(entity.getLogin());
        view.setPass(entity.getPass());
        view.setEmail(entity.getEmail());
        view.setFullName(entity.getFullName());
        view.setBirthDate(entity.getBirthDate());
        return view;
    }

    public User map(UserView view) {
        User entity = new User();
        entity.setUserId(view.getUserId());
        entity.setLogin(view.getLogin());
        entity.setPass(view.getPass());
        entity.setEmail(view.getEmail());
        entity.setFullName(view.getFullName());
        entity.setBirthDate(view.getBirthDate());
        return entity;
    }
}
