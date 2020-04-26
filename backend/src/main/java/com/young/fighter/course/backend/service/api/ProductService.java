package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.dto.ProductView;

import java.util.List;

public interface ProductService {
    ProductView save(ProductView view);

    void delete(Long id);

    ProductView findById(Long id);

    List<ProductView> findAll();

    boolean allExist(List<Long> ids);

    Long countSum(List<Long> ids);
}
