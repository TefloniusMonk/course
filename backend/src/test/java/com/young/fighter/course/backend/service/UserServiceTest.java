package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.db.repository.UserRepository;
import com.young.fighter.course.backend.dto.UserView;
import com.young.fighter.course.backend.exception.BusinessLogicException;
import com.young.fighter.course.backend.service.api.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.young.fighter.course.backend.data.UserData.getUserView;
import static com.young.fighter.course.backend.util.DatabaseUtil.clearDb;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {
    private String salt = "salt";
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private UserView userView = getUserView();

    @BeforeEach
    void before() {
        clearDb();
    }

    @Test
    void shouldSave() {
        userService.save(userView);
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        assertNotNull(users.get(0).getUserId());
        assertEquals("email", users.get(0).getEmail());
        assertEquals("login", users.get(0).getLogin());
        assertEquals(DigestUtils.sha256Hex("password" + salt), users.get(0).getPassword());
    }

    @Test
    void shouldNotSaveSecondUser() {
        userService.save(userView);
        Throwable throwable = catchThrowable(() -> userService.save(userView));
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        assertThat(throwable instanceof BusinessLogicException);
    }

    @Test
    void shouldNotSaveSecondUserWithIdenticalEmail() {
        userService.save(userView);
        userView.setLogin("another");
        Throwable throwable = catchThrowable(() -> userService.save(userView));
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        assertThat(throwable instanceof BusinessLogicException);
    }

    @Test
    void shouldNotSaveSecondUserWithIdenticalLogin() {
        userService.save(userView);
        userView.setEmail("another");
        Throwable throwable = catchThrowable(() -> userService.save(userView));
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        assertThat(throwable instanceof BusinessLogicException);
    }

    @Test
    void shouldUpdate() {
        userView = userService.save(userView);
        userView.setPassword("nePassword");
        userView.setEmail("newEmail");
        userView.setLogin("newLogin");
        userService.save(userView);
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
        assertNotNull(users.get(0).getUserId());
        assertEquals("newEmail", users.get(0).getEmail());
        assertEquals("newLogin", users.get(0).getLogin());
        assertEquals(DigestUtils.sha256Hex("nePassword" + salt), users.get(0).getPassword());
    }

    @Test
    void shouldDelete() {
        userView = userService.save(userView);
        userService.delete(userView.getUserId());
        List<User> users = userRepository.findAll();
        assertEquals(0, users.size());
    }

    @AfterEach
    void after() {
        userRepository.deleteAll();
    }
}
