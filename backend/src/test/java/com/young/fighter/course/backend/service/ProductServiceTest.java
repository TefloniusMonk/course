package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.db.repository.ProductRepository;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.service.api.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    private List<ProductView> productViews = Arrays.asList(
            new ProductView(null, 1034L, "Product1", "Description of first product"),
            new ProductView(null, 1035L, "Product2", "Description of second product"),
            new ProductView(null, 1036L, "Product3", "Description of third product")
    );
    private List<Product> productList = Arrays.asList(
            new Product(null, 1034L, "Product1", "Description of first product", Collections.emptySet(), Collections.emptySet()),
            new Product(null, 1035L, "Product2", "Description of second product", Collections.emptySet(), Collections.emptySet()),
            new Product(null, 1036L, "Product3", "Description of third product", Collections.emptySet(), Collections.emptySet())
    );

    @BeforeEach
    void before() {
        productRepository.deleteAll();
    }

    @Test
    void shouldSave() {
        productService.save(productViews.get(0));
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        assertNotNull(products.get(0).getProductId());
        assertEquals(1034L, products.get(0).getPrice());
        assertEquals("Product1", products.get(0).getProductName());
        assertEquals("Description of first product", products.get(0).getProductDesc());
    }

    @Test
    void shouldUpdate() {
        ProductView productView = productService.save(productViews.get(0));
        productView.setPrice(1L);
        productView.setProductDesc("newDesc");
        productView.setProductName("newName");
        productService.save(productView);
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        assertNotNull(products.get(0).getProductId());
        assertEquals(1L, products.get(0).getPrice());
        assertEquals("newName", products.get(0).getProductName());
        assertEquals("newDesc", products.get(0).getProductDesc());
    }

    @Test
    void shouldDelete() {
        Product product = productRepository.save(modelMapper.map(productViews.get(0), Product.class));
        productService.delete(product.getProductId());
        assertEquals(0, productRepository.findAll().size());
    }

    @Test
    void shouldGet() {
        Product product = productRepository.save(productList.get(0));
        ProductView productView = productService.findById(product.getProductId());
        assertNotNull(productView);
        assertNotNull(productView.getProductId());
        assertEquals(1034L, productView.getPrice());
        assertEquals("Product1", productView.getProductName());
        assertEquals("Description of first product", productView.getProductDesc());
    }

    @Test
    void shouldGetAll() {
        productRepository.saveAll(productList);
        List<ProductView> actualViews = productService.findAll();
        actualViews.sort(Comparator.comparing(ProductView::getProductId));
        for (int i = 0; i < actualViews.size(); i++) {
            assertNotNull(actualViews.get(i).getProductId());
            assertEquals(actualViews.get(i).getProductId(), productList.get(i).getProductId());
            assertEquals(actualViews.get(i).getProductName(), productList.get(i).getProductName());
            assertEquals(actualViews.get(i).getProductDesc(), productList.get(i).getProductDesc());
            assertEquals(actualViews.get(i).getPrice(), productList.get(i).getPrice());
        }
    }
}
