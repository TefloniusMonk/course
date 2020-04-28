package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.security.entity.JwtRequest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface UserService {
    UserView save(UserView view);

    User save(User entity);

    void delete(Long id);

    void deleteByCustomerId(Long customerId);

    boolean existUser(Long id);

    User findById(Long id);

    Set<GrantedAuthority> getUserAuthorities(Long userId);


    String auth(JwtRequest request);
}
