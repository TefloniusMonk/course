package com.young.fighter.course.backend.data;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.UserView;

public class UserData {
    public static UserView getUserView() {
        return new UserView(null, "login", "password", "email");
    }

    public static User getUser() {
        return new User(null, "login", "password", "email", null, null);
    }
}
