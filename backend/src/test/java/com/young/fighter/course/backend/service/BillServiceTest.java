package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.*;
import com.young.fighter.course.backend.db.repository.*;
import com.young.fighter.course.backend.dto.BasketView;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.service.api.BillService;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.young.fighter.course.backend.data.BasketData.getBasket;
import static com.young.fighter.course.backend.data.CustomerData.getCustomer;
import static com.young.fighter.course.backend.data.ProductData.getProducts;
import static com.young.fighter.course.backend.data.UserData.getUser;
import static com.young.fighter.course.backend.util.DatabaseUtil.clearDb;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BillServiceTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillService billService;
    @Autowired
    private ModelMapper modelMapper;

    private User user;
    private Customer customer;
    private Basket basket;
    private List<Product> products = getProducts();
    private Bill bill;
    private BillView billView;

    @BeforeEach
    void before() {
        clearDb();
        prepareData();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void shouldSale() {
        LocalDateTime before = LocalDateTime.now();
        billService.sale(modelMapper.map(basket, BasketView.class));
        List<Bill> bills = billRepository.findAll();
        List<Basket> baskets = basketRepository.findAll();
        assertEquals(1, bills.size());
        assertEquals(1, baskets.size());
        Hibernate.initialize(bills.get(0).getCustomer());
        Hibernate.initialize(bills.get(0).getProducts());
        assertEquals(3105L, bills.get(0).getTotalSum());
        assertTrue(bills.get(0).getSaleDateTime().isAfter(before));
        assertEquals(3, bills.get(0).getProducts().size());
        assertEquals(0, baskets.get(0).getProducts().size());
    }

    @Test
    void shouldGetById() {
        BillView saved = billService.sale(modelMapper.map(basket, BasketView.class));
        BillView billView = billService.findById(user.getUserId(), saved.getBillId());
        assertNotNull(billView);
        assertNotNull(billView.getBillId());
        assertEquals(customer.getCustomerId(), billView.getCustomerId());
        assertEquals(3105L, billView.getTotalSum());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void shouldGetByCustomerId() {
        billRepository.save(new Bill(null, customer, Collections.emptyList(), LocalDateTime.now(), 0L));
        billRepository.save(new Bill(null, customer, Collections.emptyList(), LocalDateTime.now(), 0L));
        billRepository.save(new Bill(null, customer, Collections.emptyList(), LocalDateTime.now(), 0L));
        List<BillView> billView = billService.findAll(user.getUserId());
        assertEquals(3, billView.size());
    }

    private void prepareData() {
        user = userRepository.save(getUser());
        customer = customerRepository.save(getCustomer(null, user));
        user.setCustomer(customer);
        customer.setUser(user);
        userRepository.save(user);
        customer = customerRepository.save(customer);
        products = getProducts();
        products = productRepository.saveAll(products);
        basket = getBasket(customer, products);
        basketRepository.save(basket);
        customer.setBasket(basket);
        customerRepository.save(customer);
    }
}
