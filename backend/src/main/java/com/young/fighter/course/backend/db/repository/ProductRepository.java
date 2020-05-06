package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
//    boolean existsAllByProductIdIn(Collection<Long> productId);
//
////    @Query(value = "SELECT SUM(product.price) FROM course.product WHERE product_id in (?1)")
//    Long countSum(List<Long> ids);
}
