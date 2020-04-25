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

import java.util.Comparator;
import java.util.List;

import static com.young.fighter.course.backend.data.ProductData.getProductViews;
import static com.young.fighter.course.backend.data.ProductData.getProducts;
import static com.young.fighter.course.backend.util.DatabaseUtil.clearDb;
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
    private List<ProductView> productViews = getProductViews();
    private List<Product> productList = getProducts();

    @BeforeEach
    void before() {
        clearDb();
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
