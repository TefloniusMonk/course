package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product save(Product entity);

    void delete(Long id);

    Product findById(Long id);

    List<Product> findAll();
}
