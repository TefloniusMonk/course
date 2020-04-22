package com.young.fighter.course.backend.mapper;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.UserView;

public class UserMapper {
    public UserView map(User entity) {
        UserView view = new UserView();
        ///
        return view;
    }

    public User map(UserView view) {
        User entity = new User();
        ///
        return entity;
    }
}
