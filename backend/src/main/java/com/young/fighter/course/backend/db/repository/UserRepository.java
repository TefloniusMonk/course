package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLoginOrAndEmailAndAndUserIdNot(String login, String email, Long id);

    boolean existsUserByLoginOrEmail(String login, String email);
}
