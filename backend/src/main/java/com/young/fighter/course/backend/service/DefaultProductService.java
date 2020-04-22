package com.young.fighter.course.backend.service;

import com.young.fighter.course.backend.db.repository.ProductRepository;
import com.young.fighter.course.backend.dto.ProductView;
import com.young.fighter.course.backend.mapper.ProductMapper;
import com.young.fighter.course.backend.service.api.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;
    private ProductMapper mapper = new ProductMapper();

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductView save(ProductView view) {
        if (view.getProductId() != null) {
            if (productRepository.findById(view.getProductId()) != null) {
                return mapper.map(productRepository.save(mapper.map(view)));
            } else {
                System.out.println("No such entity");
            }
        }
        return mapper.map(productRepository.save(mapper.map(view)));
    }

    @Override
    public void delete(Long id) {
        if (productRepository.findById(id) != null) {
            productRepository.delete(id);
        } else {
            System.out.println("No such entity");
        }
    }

    @Override
    public ProductView findById(Long id) {
        if (productRepository.findById(id) != null) {
            return mapper.map(productRepository.findById(id));
        } else {
            System.out.println("No such entity");
        }
        return null;
    }

    @Override
    public List<ProductView> findAll() {
        return productRepository.findAll().stream()
                .map(entity -> mapper.map(entity))
                .collect(Collectors.toList());
    }
}
