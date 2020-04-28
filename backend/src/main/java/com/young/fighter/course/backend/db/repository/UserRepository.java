package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLoginOrEmailAndUserIdNot(String login, String email, Long id);


    boolean existsUserByLoginOrEmail(String login, String email);

    void deleteByCustomerCustomerId(Long id);

    boolean existsUserByCustomerCustomerId(Long id);

    @Query(nativeQuery = true, value =
            "SELECT authority\n" +
                    "FROM org.authorities\n" +
                    "WHERE authority_id IN (\n" +
                    "    SELECT authority_id\n" +
                    "    FROM org.role_authorities\n" +
                    "    WHERE role_id = (\n" +
                    "        SELECT role_id\n" +
                    "        FROM org.user_roles\n" +
                    "        WHERE user_id = 1));")
    List<String> getAuthorities(Long userId);

    Optional<User> findUserByLoginAndPassword(String login, String password);
}
