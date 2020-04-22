package com.young.fighter.course.backend.db.repository;


import com.young.fighter.course.backend.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
