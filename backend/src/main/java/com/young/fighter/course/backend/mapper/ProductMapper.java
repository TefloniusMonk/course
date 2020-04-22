package com.young.fighter.course.backend.mapper;

import com.young.fighter.course.backend.db.entity.Product;
import com.young.fighter.course.backend.dto.ProductView;

public class ProductMapper {
    public static ProductView map(Product entity) {
        ProductView view = new ProductView();
        view.setProductId(entity.getProductId());
        view.setCost(entity.getCost());
        view.setProductName(entity.getProductName());
        view.setProductDesc(entity.getProductDesc());
        return view;
    }

    public static Product map(ProductView view) {
        Product entity = new Product();
        entity.setProductId(view.getProductId());
        entity.setCost(view.getCost());
        entity.setProductName(view.getProductName());
        entity.setProductDesc(view.getProductDesc());
        return entity;
    }
}
