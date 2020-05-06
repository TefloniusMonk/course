package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.security.entity.JwtRequest;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserView> save(UserView view);

    Mono<User> save(User entity);

    Mono<Void> delete(Long id);

    Mono<Void> deleteByCustomerId(Long customerId);

    Mono<Boolean> existUser(Long id);

    Mono<User> findById(Long id);

//    Mono<Set<GrantedAuthority>> getUserAuthorities(Long userId);


    Mono<String> auth(JwtRequest request);
}
