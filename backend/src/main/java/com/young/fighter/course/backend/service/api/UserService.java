package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.UserView;

public interface UserService {
    UserView save(UserView view);

    User save(User entity);

    void delete(Long id);

    void deleteByCustomerId(Long customerId);

    boolean existUser(Long id);

    User findById(Long id);
}
