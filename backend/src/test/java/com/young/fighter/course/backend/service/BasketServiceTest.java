//package com.young.fighter.course.backend.service;
//
//import com.young.fighter.course.backend.db.entity.Basket;
//import com.young.fighter.course.backend.db.entity.Customer;
//import com.young.fighter.course.backend.db.entity.Product;
//import com.young.fighter.course.backend.db.entity.User;
//import com.young.fighter.course.backend.db.repository.BasketRepository;
//import com.young.fighter.course.backend.db.repository.CustomerRepository;
//import com.young.fighter.course.backend.db.repository.ProductRepository;
//import com.young.fighter.course.backend.db.repository.UserRepository;
//import com.young.fighter.course.backend.dto.BasketView;
//import com.young.fighter.course.backend.dto.ProductView;
//import com.young.fighter.course.backend.service.api.BasketService;
//import org.hibernate.Hibernate;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.young.fighter.course.backend.data.BasketData.getBasket;
//import static com.young.fighter.course.backend.data.BasketData.getBasketView;
//import static com.young.fighter.course.backend.data.CustomerData.getCustomer;
//import static com.young.fighter.course.backend.data.ProductData.getProducts;
//import static com.young.fighter.course.backend.data.UserData.getUser;
//import static com.young.fighter.course.backend.util.DatabaseUtil.clearDb;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class BasketServiceTest {
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private BasketRepository basketRepository;
//    @Autowired
//    private BasketService basketService;
//    @Autowired
//    private ModelMapper modelMapper;
//
//    private User user;
//    private Customer customer;
//    private Basket basket;
//    private BasketView basketView;
//    private List<Product> products = getProducts();
//    private List<ProductView> productViews;
//
//
//    @BeforeEach
//    void before() {
//        clearDb();
//        user = userRepository.save(getUser()).block();
//        prepareData();
//    }
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void shouldSave() {
//        BasketView actual = basketService.saveToBasket(basketView).block();
//        List<Customer> actualCustomer = customerRepository.findAll().collectList().block();
//        assertEquals(1, actualCustomer.size());
//        Hibernate.initialize(actualCustomer.get(0).getBasket());
//        assertEquals(actual.getBasketId(), actualCustomer.get(0).getBasket().getBasketId());
//        assertEquals(3, actual.getProducts().size());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void shouldUpdate() {
//        basketRepository.save(basket);
//        basketView.getProducts().add(modelMapper.map(
//                productRepository.save(
//                        new Product(null, 100L, "NewProductName",
//                                "NewProductDesc", Collections.emptyList(), Collections.emptyList(), Collections.emptyList())),
//                ProductView.class));
//        Long totalPrice = basketView.getProducts().stream().mapToLong(ProductView::getPrice).sum();
//        basketView.setTotalCost(totalPrice);
//        BasketView actual = basketService.saveToBasket(basketView).block();
//        List<Customer> actualCustomer = customerRepository.findAll().collectList().block();
//        assertEquals(1, actualCustomer.size());
//        Hibernate.initialize(actualCustomer.get(0).getBasket());
//        assertEquals(actual.getBasketId(), actualCustomer.get(0).getBasket().getBasketId());
//        assertEquals(4, actual.getProducts().size());
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void shouldClearBasket() {
//        basket = basketRepository.save(basket).block();
//        basketService.clear(basket.getBasketId());
//        Basket actual = basketRepository.findById(basket.getBasketId()).block();
//        Hibernate.initialize(actual.getProducts());
//        assertEquals(0, actual.getProducts().size());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void shouldGetByCustomerId() {
//        basket = basketRepository.save(basket).block();
//        Hibernate.initialize(basket.getCustomer());
//        Hibernate.initialize(basket.getCustomer().getUser());
//        BasketView actual = basketService.findByUserId(basket.getCustomer().getUser().getUserId()).block();
//        Hibernate.initialize(actual.getProducts());
//        assertEquals(basket.getCustomer().getCustomerId(), actual.getCustomerId());
//        assertEquals(basket.getBasketId(), actual.getBasketId());
//        assertEquals(basket.getTotalCost(), actual.getTotalCost());
//        assertEquals(3, actual.getProducts().size());
//    }
//
//    private void prepareData() {
//        customer = customerRepository.save(getCustomer(null, null)).block();
//        user.setCustomer(customer);
//        customer.setUser(user);
//        userRepository.save(user);
//        customerRepository.save(customer);
//        products = productRepository.saveAll(products).collectList().block();
//        productViews = products.stream().map(product -> modelMapper.map(product, ProductView.class)).collect(Collectors.toList());
//        basketView = getBasketView(customer.getCustomerId(), productViews);
//        basket = getBasket(customer, productViews.stream().map(productView -> modelMapper.map(productView, Product.class)).collect(Collectors.toList()));
//        basket.setProducts(products);
//    }
//
//}
