package com.young.fighter.course.backend.db.repository.data;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.db.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserData implements UserRepository {
    private static List<User> users = Stream.of(
            new User(1L, "login1", "pass1", "email1", "fullname1", LocalDate.now()),
            new User(2L, "login2", "pass2", "email3", "fullname4", LocalDate.now())
    ).collect(Collectors.toList());

    @Override
    public User save(User entity) {
        users.add(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        users.removeIf(user -> user.getUserId().equals(id));
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
