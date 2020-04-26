package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsAllByProductIdIn(Collection<Long> productId);

    @Query(value = "SELECT SUM(product.price) FROM course.product WHERE product_id in (?1)", nativeQuery = true)
    Long countSum(List<Long> ids);
}
