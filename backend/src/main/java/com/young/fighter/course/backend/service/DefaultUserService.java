package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.repository.UserRepository;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.mapper.UserMapper;
import com.young.fighter.course.backend.service.api.UserService;

public class DefaultUserService implements UserService {
    private UserRepository userRepository;
    private UserMapper mapper = new UserMapper();

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserView save(UserView view) {
        if (view.getUserId() != null) {
            if (userRepository.findById(view.getUserId()) != null) {
                return mapper.map(userRepository.save(mapper.map(view)));
            } else {
                System.out.println("No such entity");
            }
        }
        return mapper.map(userRepository.save(mapper.map(view)));
    }

    @Override
    public void delete(Long id) {
        if (userRepository.findById(id) != null) {
            userRepository.delete(id);
        } else {
            System.out.println("No such entity");
        }
    }

    @Override
    public UserView findById(Long id) {
        if (userRepository.findById(id) != null) {
            return mapper.map(userRepository.findById(id));
        } else {
            System.out.println("No such entity");
        }
        return null;
    }
}
