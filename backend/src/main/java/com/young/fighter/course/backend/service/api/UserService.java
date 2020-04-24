package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.UserView;

public interface UserService {
    UserView save(UserView entity);

    void delete(Long id);

//    UserView findById(Long id);  Maybe will need later
}
