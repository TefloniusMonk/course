package com.young.fighter.course.backend.mapper;

import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.dto.ProductView;

public class ProductMapper {
    public ProductView map(Product entity) {
        ProductView view = new ProductView();
        ///
        return view;
    }

    public Product map(ProductView view) {
        Product entity = new Product();
        ///
        return entity;
    }
}
