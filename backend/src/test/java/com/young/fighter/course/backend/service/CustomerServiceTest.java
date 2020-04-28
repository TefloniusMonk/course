package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Customer;
import com.young.fighter.course.backend.db.entity.User;
import com.young.fighter.course.backend.db.repository.BasketRepository;
import com.young.fighter.course.backend.db.repository.CustomerRepository;
import com.young.fighter.course.backend.db.repository.UserRepository;
import com.young.fighter.course.backend.dto.CustomerView;
import com.young.fighter.course.backend.service.api.CustomerService;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static com.young.fighter.course.backend.data.CustomerData.getCustomer;
import static com.young.fighter.course.backend.data.CustomerData.getCustomerView;
import static com.young.fighter.course.backend.data.UserData.getUser;
import static com.young.fighter.course.backend.util.DatabaseUtil.clearDb;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ModelMapper modelMapper;

    private User user;
    private Customer customer;
    private CustomerView customerView;

    @BeforeEach
    void before() {
        clearDb();
        user = userRepository.save(getUser());
        customerView = getCustomerView(null, user.getUserId());
        customer = getCustomer(null, null);
    }

    @Test
    @Transactional
    void shouldSave() {
        CustomerView saved = customerService.save(customerView);
        User actualUser = getUserFromDb(saved);
        assertNotNull(actualUser.getCustomer().getCustomerId());
        assertNotNull(saved.getCustomerId());
        assertEquals(actualUser.getCustomer().getCustomerId(), saved.getCustomerId());
        assertEquals(actualUser.getUserId(), saved.getUserId());
        assertEquals("email", saved.getEmail());
        assertEquals("Full Name", saved.getFullName());
    }

    @Test
    @Transactional
    void shouldUpdate() {
        customerService.save(customerView);
        customerView.setBirthDate(LocalDate.now());
        customerView.setEmail("newEmail");
        customerView.setFullName("newName");
        CustomerView saved = customerService.save(customerView);
        User actualUser = getUserFromDb(saved);
        assertNotNull(actualUser.getCustomer().getCustomerId());
        assertNotNull(saved.getCustomerId());
        assertEquals(actualUser.getCustomer().getCustomerId(), saved.getCustomerId());
        assertEquals(actualUser.getUserId(), saved.getUserId());
        assertEquals("newEmail", saved.getEmail());
        assertEquals("newName", saved.getFullName());
    }

    @Test
    void shouldDelete() {
        bindAndSave();
        customerService.delete(customer.getCustomerId());
        assertEquals(0, userRepository.findAll().size());
        assertEquals(0, customerRepository.findAll().size());
    }

    @Test
    @Transactional
    void shouldGet() {
        bindAndSave();
        Hibernate.initialize(customer.getUser());
        CustomerView actual = customerService.findByUserId(customer.getUser().getUserId());
        assertEquals("email", actual.getEmail());
        assertEquals("Full Name", actual.getFullName());
    }

    private void bindAndSave() {
        user = userRepository.save(user);
        customer = customerRepository.save(customer);
        user.setCustomer(customer);
        customer.setUser(user);
        userRepository.save(user);
        customerRepository.save(customer);
    }

    User getUserFromDb(CustomerView saved) {
        User actualUser = userRepository.getOne(saved.getUserId());
        Hibernate.initialize(actualUser.getCustomer());
        return actualUser;
    }
}
