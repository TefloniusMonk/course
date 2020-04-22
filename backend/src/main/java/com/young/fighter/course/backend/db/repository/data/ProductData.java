package com.young.fighter.course.backend.db.repository.data;

import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.db.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;

public class ProductData implements ProductRepository {
    private List<Product> products = Arrays.asList(
            new Product(1L, 1222L, "Name1", "desc"),
            new Product(2L, 1223L, "Name2", "desc2"),
            new Product(3L, 1224L, "Name3", "desc3"),
            new Product(4L, 1225L, "Name4", "desc4")
    );

    @Override
    public Product save(Product entity) {
        products.add(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        products.removeIf(product -> product.getProductId().equals(id));
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.getProductId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }
}
