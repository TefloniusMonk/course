package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
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
    Flux<String> getAuthorities(Long userId);

    Mono<User> findUserByLoginAndPassword(String login, String password);
}
