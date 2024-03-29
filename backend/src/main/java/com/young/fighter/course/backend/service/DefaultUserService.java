package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.db.repository.UserRepository;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DefaultUserService implements UserService {
    private String salt = "salt";
    private UserRepository userRepository;
    private final ModelMapper mapper;

    public DefaultUserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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
    public void delete(Long id) {
        if (userRepository.findById(id).isPresent()) {
            log.info("Deleting customer with id: {}", id);
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
}
