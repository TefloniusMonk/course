package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.*;
import com.young.fighter.course.backend.db.repository.*;
import com.young.fighter.course.backend.dto.BillView;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.service.api.BillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static com.young.fighter.course.backend.data.BillData.getBill;
import static com.young.fighter.course.backend.data.BillData.getBillView;
import static com.young.fighter.course.backend.data.CustomerData.getCustomer;
import static com.young.fighter.course.backend.data.ProductData.getProducts;
import static com.young.fighter.course.backend.data.UserData.getUser;
import static com.young.fighter.course.backend.util.DatabaseUtil.clearDb;

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
    void shouldSale() {

    }

    @Test
    void shouldGetById() {

    }

    @Test
    void shouldGetByCustomerId() {

    }

    private void prepareData() {
        user = userRepository.save(getUser());
        customer = customerRepository.save(getCustomer(null, null));
        user.setCustomer(customer);
        customer.setUser(user);
        userRepository.save(user);
        customerRepository.save(customer);
        products = getProducts();
        products = productRepository.saveAll(products);
        basket.setProducts(products);
        basketRepository.save(basket);
        billView = getBillView(
                customer.getCustomerId(),
                products.stream().map(product -> modelMapper.map(product, ProductView.class)).collect(Collectors.toList())
        );
        bill = getBill(customer, products);
    }
}
