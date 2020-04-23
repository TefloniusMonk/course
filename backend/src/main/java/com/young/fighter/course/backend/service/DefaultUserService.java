package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.db.repository.UserRepository;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultUserService implements UserService {
    private UserRepository userRepository;
    private final ModelMapper mapper;

    public DefaultUserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserView save(UserView view) {
        if (view.getUserId() != null) {
            if (userRepository.findById(view.getUserId()).isPresent()) {
                UserView userView = mapper.map(userRepository.save(mapper.map(view, User.class)), UserView.class);
                log.info("Creating new product: {}", userView.toString());
                return userView;
            } else {
                log.error("Cannot find user with id: {}", view.getUserId());
                throw new BusinessLogicException("entity.not.exist");
            }
        }
        log.info("Updating user: {}", view.toString());
        return mapper.map(userRepository.save(mapper.map(view, User.class)), UserView.class);
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
    public UserView findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return mapper.map(userRepository.findById(id), UserView.class);
        } else {
            log.error("User with id {} doesn't exist", id);
            throw new BusinessLogicException("entity.not.exist");
        }
    }
}
