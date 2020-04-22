package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User entity);

    void delete(Long id);

    User findById(Long id);

    List<User> findAll();
}
