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
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public UserView save(UserView view) {
        view.setPassword(DigestUtils.sha256Hex(view.getPassword() + salt));
        if (view.getUserId() != null) {
            if (userRepository.findById(view.getUserId()).isPresent()
                    &&
                    !userRepository.existsUserByLoginOrEmailAndUserIdNot(view.getLogin(), view.getEmail(), view.getUserId())) {
                UserView userView = mapper.map(userRepository.save(mapper.map(view, User.class)), UserView.class);
                log.info("Creating new user with id: {}", userView.getUserId());
                return userView;
            } else {
                log.error("Cannot find user with id: {}", view.getUserId());
                throw new BusinessLogicException("entity.not.exist");
            }
        }
        if (!userRepository.existsUserByLoginOrEmail(view.getLogin(), view.getEmail())) {
            log.info("Updating user with id: {}", view.getUserId());
            return mapper.map(userRepository.save(mapper.map(view, User.class)), UserView.class);
        } else {
            log.error("Can not create user with email: {} and login {}. They are in use", view.getEmail(), view.getLogin());
            throw new BusinessLogicException("user.in.use");
        }
    }

    @Override
    public User save(User entity) {
        log.info("Updating user: {}", entity.getUserId());
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userEntity = optionalUser.get();
            Hibernate.initialize(userEntity);
            log.info("Deleting user with id: {}", id);
            userRepository.deleteById(id);
        } else {
            log.error("Can not delete user with id: {}", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    public void deleteByCustomerId(Long customerId) {
        if (userRepository.existsUserByCustomerCustomerId(customerId)) {
            userRepository.deleteByCustomerCustomerId(customerId);
        } else {
            log.error("Can not delete user with customerId: {}", customerId);
            throw new BusinessLogicException("entity.not.exist");
        }
    }


    @Override
    public boolean existUser(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            log.error("User with id {} doesn't exist", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }

    @Override
    public Set<GrantedAuthority> getUserAuthorities(Long userId) {
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        userRepository.getAuthorities(userId).forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority)));
        return authorities;
    }

    @Override
    @Transactional
    public String auth(JwtRequest request) {
        Optional<User> optionalUser = userRepository.findUserByLoginAndPassword(request.getLogin(), (DigestUtils.sha256Hex(request.getPassword() + salt)));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Hibernate.initialize(user.getCustomer());
            return jwtTokenUtil.generateToken(new JwtUser(user.getCustomer() == null ? null : user.getCustomer().getCustomerId(), user.getUserId(), userRepository.getAuthorities(user.getUserId())));
        }
        log.error("Wrong authorization try");
        throw new BusinessLogicException("forbidden");
    }
}
