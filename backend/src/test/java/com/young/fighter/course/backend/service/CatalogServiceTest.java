package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.entity.Catalog;
import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.db.repository.CatalogRepository;
import com.young.fighter.course.backend.db.repository.ProductRepository;
import com.young.fighter.course.backend.dto.CatalogView;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.service.api.CatalogService;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.young.fighter.course.backend.data.CatalogData.getCatalog;
import static com.young.fighter.course.backend.data.CatalogData.getCatalogView;
import static com.young.fighter.course.backend.data.ProductData.getProductViews;
import static com.young.fighter.course.backend.data.ProductData.getProducts;
import static com.young.fighter.course.backend.util.DatabaseUtil.clearDb;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CatalogServiceTest {
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    private List<Product> products = getProducts();
    private List<ProductView> productViews = getProductViews();
    private Catalog catalog;
    private CatalogView catalogView;

    @BeforeEach
    void before() {
        clearDb();
        products = productRepository.saveAll(products);
        products.forEach(product -> {
            Hibernate.initialize(product.getBaskets());
            Hibernate.initialize(product.getCatalogs());
        });
        catalog = getCatalog(products);
        catalogView = getCatalogView(products.stream().map(entity -> modelMapper.map(entity, ProductView.class)).collect(Collectors.toList()));
    }

    @Test
    void shouldSave() {
        CatalogView returned = catalogService.save(catalogView);
        List<Catalog> actual = catalogRepository.findAll();
        assertEquals(1, actual.size());
        assertNotNull(returned.getCatalogId());
        assertEquals("catalogName", returned.getCatalogName());
        assertEquals(3, returned.getProducts().size());
    }

    @Test
    void shouldUpdate() {
        catalogView = catalogService.save(catalogView);
        catalogView.setCatalogName("catalogNameNew");
        catalogView.setProducts(Collections.emptyList());
        CatalogView returned = catalogService.save(catalogView);
        List<Catalog> actual = catalogRepository.findAll();
        assertEquals(1, actual.size());
        assertNotNull(returned.getCatalogId());
        assertEquals("catalogNameNew", returned.getCatalogName());
        assertEquals(0, returned.getProducts().size());
    }

    @Test
    void shouldDelete() {
        Catalog saved = catalogRepository.save(catalog);
        catalogService.delete(saved.getCatalogId());
        List<Catalog> actual = catalogRepository.findAll();
        assertEquals(0, actual.size());
    }

    @Test
    void shouldGetById() {
        Catalog saved = catalogRepository.save(catalog);
        CatalogView actual = catalogService.findById(saved.getCatalogId());
        assertEquals(saved.getCatalogId(), actual.getCatalogId());
        assertEquals(saved.getCatalogName(), actual.getCatalogName());
        assertEquals(3, actual.getProducts().size());
    }

    @Test
    void shouldGetAll() {
        catalogRepository.save(getCatalog(products));
        catalogRepository.save(getCatalog(products));
        catalogRepository.save(getCatalog(products));
        List<CatalogView> actual = catalogService.findAll();
        assertEquals(3, actual.size());
    }

}
