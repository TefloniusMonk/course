package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.db.repository.UserRepository;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.security.entity.JwtRequest;
import com.young.fighter.course.backend.security.entity.JwtUser;
import com.young.fighter.course.backend.security.util.JwtTokenUtil;
import com.young.fighter.course.backend.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class DefaultUserService implements UserService {
    private String salt = "salt";
    private UserRepository userRepository;
    private final ModelMapper mapper;
    private final JwtTokenUtil jwtTokenUtil;

    public DefaultUserService(UserRepository userRepository, ModelMapper mapper, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Mono<UserView> save(UserView view) {
        view.setPassword(DigestUtils.sha256Hex(view.getPassword() + salt));
        if (view.getUserId() != null) {
            if (!userRepository.existsUserByLoginOrEmailAndUserIdNot(view.getLogin(), view.getEmail(), view.getUserId())) {
                log.error("Can not create user with email: {} and login {}. They are in use", view.getEmail(), view.getLogin());
                throw new BusinessLogicException("user.in.use");
            }
            return userRepository.findById(view.getUserId())
                    .map(user -> {
                        log.info("Creating new user with id: {}", user.getUserId());
                        return mapper.map(user, UserView.class);
                    });
        }
        if (!userRepository.existsUserByLoginOrEmail(view.getLogin(), view.getEmail())) {
            return userRepository.save(mapper.map(view, User.class))
                    .map(user -> {
                        log.info("Updating user with id: {}", view.getUserId());
                        return mapper.map(user, UserView.class);
                    });
        } else {
            log.error("Can not create user with email: {} and login {}. They are in use", view.getEmail(), view.getLogin());
            throw new BusinessLogicException("user.in.use");
        }
    }

    @Override
    public Mono<User> save(User entity) {
        log.info("Updating user: {}", entity.getUserId());
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public Mono<Void> delete(Long id) {
        return userRepository.deleteById(id)
                .doOnError(error -> {
                    log.error("Can not delete user with id: {}", id);
                    throw new BusinessLogicException("entity.not.exist");
                });
    }

    @Override
    public Mono<Void> deleteByCustomerId(Long customerId) {
        if (userRepository.existsUserByCustomerCustomerId(customerId)) {
            return userRepository.deleteByCustomerCustomerId(customerId);
        } else {
            log.error("Can not delete user with customerId: {}", customerId);
            throw new BusinessLogicException("entity.not.exist");
        }
    }


    @Override
    public Mono<Boolean> existUser(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Mono<User> findById(Long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                            log.error("User with id {} doesn't exist", id);
                            throw new BusinessLogicException("entity.not.exist");
                        }
                        )
                );

    }

//    @Override
//    public Set<GrantedAuthority> getUserAuthorities(Long userId) {
//        HashSet<GrantedAuthority> authorities = new HashSet<>();
//        userRepository.getAuthorities(userId).forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority)));
//        return authorities;
//    }

    @Override
    @Transactional
    public Mono<String> auth(JwtRequest request) {
        return userRepository.findUserByLoginAndPassword(request.getLogin(), (DigestUtils.sha256Hex(request.getPassword() + salt)))
                .zipWhen(user -> {
                    return userRepository.getAuthorities(user.getUserId()).collectList();
                })
                .map(pair -> { // user, authorities
                    return jwtTokenUtil.generateToken(
                            new JwtUser(pair.getT1().getCustomerId(),
                                    pair.getT1().getUserId(),
                                    pair.getT2()));
                });
    }
}
