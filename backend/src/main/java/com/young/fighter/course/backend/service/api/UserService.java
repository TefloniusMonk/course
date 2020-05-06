package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.security.entity.JwtRequest;
import org.springframework.security.core.GrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface UserService {
    Mono<UserView> save(UserView view);

    Mono<User> save(User entity);

    void delete(Long id);

    void deleteByCustomerId(Long customerId);

    boolean existUser(Long id);

    Mono<User> findById(Long id);

    Mono<Set<GrantedAuthority>> getUserAuthorities(Long userId);


    Mono<String> auth(JwtRequest request);
}
